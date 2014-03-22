package giki.runtime;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Container {
	Stream createInput();
	Stream createOutput();
	
	public static class Default extends Symbol implements Container {
		public List<Symbol> list;
		
		public Default() {
			this(new ArrayList<Symbol>());
		}
		
		public Default(List<Symbol> list) {
			this.list = list;
		}

		@Override
		public Stream createInput() {
			return new IOBuffer(list);
		}
		
		@Override
		public Stream createOutput() {
			return new IOBuffer(list);
		}
		
		@Override
		public String toString() {
			return list.toString();
		}
		
		public Default with(Symbol item) {
			list.add(item);
			return this;
		}
	}
	
	public static class File extends Symbol implements Container {
		private String filePath;
		
		public File(String filePath) {
			this.filePath = filePath;
		}
		
		@Override
		public Stream createInput() {
			try {
				return new Stream() {
//					StopWatch sw;
					FileInputStream fileIn;
					BufferedInputStream bufferedIn;
					Symbol current;
					
					{
						fileIn = new FileInputStream(filePath);
						bufferedIn = new BufferedInputStream(fileIn, 1024 * 10);
//						sw = new StopWatch();
						readNext();
					}
					
					private void readNext() throws IOException {
//						sw.start();
						int nextByte = bufferedIn.read();
//						sw.stop();
						if(nextByte != -1)
							current = Symbol.getIdentifier("" + (char)nextByte);
						else
							current = null;
					}

					@Override
					public boolean atEnd() {
						return current == null;
					}

					@Override
					public Symbol peek() {
						return current;
					}

					@Override
					public Symbol consume() {
						Symbol symbol = current;
						try {
							readNext();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return symbol;
					}

					@Override
					public void closeInput() {
						try {
//							sw.printElapsed(System.out, "Read " + filePath);
							bufferedIn.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					@Override
					public Symbol peek(int ahead) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void put(Symbol symbol) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void closeOutput() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void pipe() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public Symbol reify() {
						return File.this;
					}
					
					@Override
					public String toString() {
						return File.this.toString();
					}
				};
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		public Stream createOutput() {
			try {
				return new Stream() {
					FileOutputStream fileOut;
					BufferedOutputStream bufferedOut;
					
					{
						fileOut = new FileOutputStream(filePath);
						bufferedOut = new BufferedOutputStream(fileOut, 1024 * 10);
					}
					
					@Override
					public void put(Symbol symbol) {
						try {
//							fileOut.write(symbol.toString());
							bufferedOut.write(((Symbol.Identifier)symbol).name.charAt(0));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

//					@Override
//					public Input asInput() {
//						return File.this.createInput();
//					}

					@Override
					public void closeOutput() {
						try {
							bufferedOut.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}

					@Override
					public boolean atEnd() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public Symbol peek() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Symbol consume() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void closeInput() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public Symbol peek(int ahead) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void pipe() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public Symbol reify() {
						return File.this;
					}
					
					@Override
					public String toString() {
						return File.this.toString();
					}

//					@Override
//					public void setLimit(int limit) {
//						// TODO Auto-generated method stub
//						
//					}
				};
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		public String toString() {
			return "file://" + filePath;
		}
	}
}
