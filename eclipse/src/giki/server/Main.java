package giki.server;

import giki.diagnostics.StopWatch;
import giki.parser.FileResourceStore;
import giki.parser.ResourceStore;
import giki.runtime.Container;
import giki.runtime.Input;
import giki.runtime.Output;
import giki.runtime.Symbol;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		int portNumber = 8888;
		
		ServerSocket serverSocket = new ServerSocket(portNumber);
	    
	    System.out.println("Server running.");
		
		try {
			while(true) {
			    Socket clientSocket = serverSocket.accept();
			    StopWatch stopWatch = new StopWatch();
				stopWatch.start();
			    PrintStream out =
			        new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			    BufferedReader in = new BufferedReader(
			        new InputStreamReader(clientSocket.getInputStream()));
			    
//		    	char[] cbuf = new char[1024]; 
//			    StringBuilder requestBuilder = new StringBuilder();
//			    while(true) { 
//			    	int readChars = in.read(cbuf, 0, cbuf.length);
//			    	requestBuilder.append(cbuf);
//			    	if(readChars == -1)
//			    		break;
//			    }
//			    String request = requestBuilder.toString();
			    String request = in.readLine();
			    
			    System.out.println("Handling parse request: \"" + request + "\".");
			    
			    String[] requestSplit = request.split(" ");
			    
//			    String filePath = requestSplit[0];
			    String filePath = request;
			    
			    File file = new File(filePath);
				String directory = file.getParentFile().getCanonicalPath() + File.separator;
				String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
				
				ResourceStore resourceStore = new FileResourceStore(directory);
			    
				final String[] gikiArgs = new String[requestSplit.length - 1];
				System.arraycopy(requestSplit, 1, gikiArgs, 0, gikiArgs.length);
				giki.server.Response response = new Response(resourceStore, out);
				
				final ArrayList<Symbol> gikiArgsBuilder = new ArrayList<Symbol>();
//				for(int i = 0; i < args.length; i++) {
//					gikiArgsBuilder.add(Symbol.getIdentifier(args[i]));
//				}

				Input input = new Container.Default(gikiArgsBuilder).createInput();
				Output output = new Container.Default().createOutput();
				
				response.parse(name, input, output);
			    
//			    out.println("You requested: " + filePath);
			    out.flush();
				clientSocket.close();
				stopWatch.stop();
				stopWatch.printElapsed(System.out, "Response");
			}
		} catch(IOException e) {
			
		} finally {
			serverSocket.close();
		}
	}
}
