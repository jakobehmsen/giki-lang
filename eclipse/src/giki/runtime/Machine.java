package giki.runtime;

public class Machine {
	public static class Frame implements Input, Output {
		public Frame parent;
		public int i;
		public final Input input;
		public final Output output;
		public final Instruction[] code;
		
		public Instruction call;
		
		// TODO: Consider: Maybe variables should map to singulars and inters to plurals?
		public Symbol[] variables; 
		public Stream[] inters;
		
		public static final int INPUT_SOURCE = 0;
		public static final int INPUT_INTER = 1;
		public static final int INPUT_VARIABLE = 2;
		public static final int INPUT_MAP_SLOT = 3;
		
		public static final int OUTPUT_TARGET = 0;
		public static final int OUTPUT_INTER = 1;
		public static final int OUTPUT_VARIABLE = 2;
		public static final int OUTPUT_MAP_SLOT = 3;
		
		public Frame(Frame parent, Input input, Output output, Instruction[] code, Symbol[] variables, int interCount) {
			this.parent = parent;
			this.input = input;
			this.output = output;
			this.code = code;
			this.variables = variables;
			inters = new Stream[interCount];
		}

		@Override
		public void put(Symbol symbol) {
			switch(call.outputType) {
			case Frame.OUTPUT_VARIABLE: {
				variables[call.outputOrdinal] = symbol;
				break;
			}
			}
		}

		@Override
		public void closeOutput() { }

		@Override
		public boolean atEnd() {
			switch(call.inputType) {
			case Frame.INPUT_VARIABLE:
				return false;
			}
			
			return false;
		}

		@Override
		public Symbol peek() {
			switch(call.inputType) {
			case Frame.INPUT_VARIABLE: {
				return variables[call.inputOrdinal];
			}
			}
			
			return null;
		}

		@Override
		public Symbol consume() {
			switch(call.inputType) {
			case Frame.INPUT_VARIABLE: {
				return variables[call.inputOrdinal];
			}
			}
			
			return null;
		}

		@Override
		public void closeInput() { }

		@Override
		public Symbol peek(int ahead) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public static final int RESULT_ABSENT = 0;
	public static final int RESULT_ACCEPTED = 1;
	public static final int RESULT_REJECTED = 2;
	
	private int result;
	private Frame frame;
	
	public Machine(Input input, Output output, Instruction[] code, Symbol[] variables, int interCount) {
		frame = new Frame(null, input, output, code, variables, interCount);
	}
	
	public int getResult() {
		return result;
	}
	
	private final void put(Instruction instruction, Symbol symbol) {
		switch(instruction.outputType) {
		case Frame.OUTPUT_TARGET:
			frame.output.put(symbol);
			break;
		case Frame.OUTPUT_INTER:
			frame.inters[instruction.outputOrdinal].put(symbol);
			break;
		case Frame.OUTPUT_VARIABLE:
			frame.variables[instruction.outputOrdinal] = symbol;
			break;
		case Frame.OUTPUT_MAP_SLOT: {
			// HACK: Only a single level of slots is assumed
			Symbol.Map map = (Symbol.Map)frame.variables[instruction.outputOrdinal];
			map.put(instruction.outputSlotPath[0], symbol);
			break;
		}
		}
	}
	
	private final Symbol peek(Instruction instruction) {
		switch(instruction.inputType) {
		case Frame.INPUT_SOURCE:
			return frame.input.peek();
		case Frame.INPUT_INTER:
			return frame.inters[instruction.inputOrdinal].peek();
		case Frame.INPUT_VARIABLE:
			return frame.variables[instruction.inputOrdinal];
		case Frame.INPUT_MAP_SLOT: {
			// HACK: Only a single level of slots is assumed
			Symbol.Map map = (Symbol.Map)frame.variables[instruction.inputOrdinal];
			return map.get(instruction.inputSlotPath[0]);
		}
		}
		
		return null;
	}
	
	private final Symbol consume(Instruction instruction) {
		switch(instruction.inputType) {
		case Frame.INPUT_SOURCE:
			return frame.input.consume();
		case Frame.INPUT_INTER:
			return frame.inters[instruction.inputOrdinal].consume();
		case Frame.INPUT_VARIABLE:
			return frame.variables[instruction.inputOrdinal];
		case Frame.INPUT_MAP_SLOT: {
			// HACK: Only a single level of slots is assumed
			Symbol.Map map = (Symbol.Map)frame.variables[instruction.inputOrdinal];
			return map.get(instruction.inputSlotPath[0]);
		}
		}
		
		return null;
	}

	private final boolean atEnd(Instruction instruction) {
		switch(instruction.inputType) {
		case Frame.INPUT_SOURCE:
			return frame.input.atEnd();
		case Frame.INPUT_INTER:
			return frame.inters[instruction.inputOrdinal].atEnd();
		case Frame.INPUT_VARIABLE:
			return false;
		case Frame.INPUT_MAP_SLOT:
			return false;
		}
		
		return false;
	}
	
	private int maxCheckPoints = 10000;
	
	public void evaluate() {
		int checkPoints = 0;
		finish:
		while(true) {
			Instruction instruction = frame.code[frame.i];
			
			switch(instruction.type) {
			case Instruction.TYPE_QUOTE: {
				Symbol symbol = (Symbol)instruction.operand;
				put(instruction, symbol);
				break;
			}
			case Instruction.TYPE_ACCEPT_FINISH: {
				result = RESULT_ACCEPTED;
				break finish;
			}
			case Instruction.TYPE_REJECT_FINISH: {
				result = RESULT_REJECTED;
				break finish;
			}
			case Instruction.TYPE_ACCEPT_FRAME: {
				frame = frame.parent;
				break;
			}
			case Instruction.TYPE_REJECT_FRAME: {
				frame = frame.parent;
				frame.i = frame.code[frame.i].falseJump;
				continue;
			}
			case Instruction.TYPE_PEEK: {
				Symbol symbol = peek(instruction);
				if(symbol == null) {
					frame.i = instruction.falseJump;
					continue;
				}
				put(instruction, symbol);
				break;
			}
			case Instruction.TYPE_MOVE: {
				Symbol symbol = consume(instruction);
				if(symbol == null) {
					frame.i = instruction.falseJump;
					continue;
				}
				put(instruction, symbol);
				break;
			}
			case Instruction.TYPE_CONSUME: {
				Symbol symbol = consume(instruction);
				if(symbol == null) {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			case Instruction.TYPE_JUMP: {
				frame.i = (int)instruction.operand;
				continue;
			}
			case Instruction.TYPE_PIPE_START: {
				frame.inters[instruction.outputOrdinal] = new IOBuffer();
				break;
			}
			case Instruction.TYPE_PIPE_INTER: {
				frame.inters[instruction.outputOrdinal].pipe();
				break;
			}
			case Instruction.TYPE_PIPE_END: {
				frame.inters[instruction.outputOrdinal].pipe();
				break;
			}
			case Instruction.TYPE_SET_OUTPUT_NEW_INTER: {
				frame.inters[instruction.outputOrdinal] = new IOBuffer();
				break;
			}
			case Instruction.TYPE_PEEK_EQUALS_CONSUME: {
				Symbol symbol = peek(instruction);
				if(symbol == null) {
					frame.i = instruction.falseJump;
					continue;
				}
				Symbol other = (Symbol)instruction.operand;
				if(!symbol.equals(other)) {
					frame.i = instruction.falseJump;
					continue;
				}
				consume(instruction);
				break;
			}
			case Instruction.TYPE_EOF: {
				if(!atEnd(instruction)) {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			case Instruction.TYPE_NEW_MAP: {
				put(instruction, new Symbol.Map());
				break;
			}
			case Instruction.TYPE_PUSH_FRAME: {
				CodeBuilder.Build moduleBuild = (CodeBuilder.Build)instruction.operand;
				
				Input input = null;
				
				switch(instruction.inputType) {
				case Frame.INPUT_SOURCE:
					input = frame.input;
					break;
				case Frame.INPUT_INTER:
					input = frame.inters[instruction.inputOrdinal];
					break;
				case Frame.INPUT_VARIABLE:
					input = frame;
					frame.call = instruction;
					break;
				}
				
				Output output = null;
				
				switch(instruction.outputType) {
				case Frame.OUTPUT_TARGET:
					output = frame.output;
					break;
				case Frame.OUTPUT_INTER:
					output = frame.inters[instruction.outputOrdinal];
					break;
				case Frame.OUTPUT_VARIABLE:
					output = frame;
					frame.call = instruction;
					break;
				}
				
				frame = new Frame(frame, input, output, moduleBuild.code, new Symbol[moduleBuild.variableCount], moduleBuild.interCount);
				continue;
			}
			case Instruction.TYPE_PEEK_GTE: {
				// TODO: Support comparison among more than Identifiers
				Symbol symbol = peek(instruction);
				if(symbol != null && symbol instanceof Symbol.Identifier) {
					Symbol.Identifier lhs = (Symbol.Identifier)symbol;
					Symbol.Identifier rhs = (Symbol.Identifier)instruction.operand;
					if(lhs.name.charAt(0) < rhs.name.charAt(0)) {
						frame.i = instruction.falseJump;
						continue;
					}
				}
				break;
			}
			case Instruction.TYPE_PEEK_LTE: {
				// TODO: Support comparison among more than Identifiers
				Symbol symbol = peek(instruction);
				if(symbol != null && symbol instanceof Symbol.Identifier) {
					Symbol.Identifier lhs = (Symbol.Identifier)symbol;
					Symbol.Identifier rhs = (Symbol.Identifier)instruction.operand;
					if(lhs.name.charAt(0) > rhs.name.charAt(0)) {
						frame.i = instruction.falseJump;
						continue;
					}
				}
				break;
			}
			case Instruction.TYPE_MAP_UNION: {
				Symbol lhs = consume(instruction);
				Symbol rhs = consume(instruction);
				
				if(lhs != null && rhs != null && lhs instanceof Symbol.Map && rhs instanceof Symbol.Map) {
					Symbol union = ((Symbol.Map)lhs).composeWith((Symbol.Map)rhs);
					put(instruction, union);
				} else {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			case Instruction.TYPE_FILE: {
				Symbol symbol = consume(instruction);
				
				if(symbol instanceof Symbol.Identifier) {
					Container.File file = new Container.File(((Symbol.Identifier)symbol).name);
					put(instruction, file);
				} else {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			
			case Instruction.TYPE_AS_INTER_INPUT: {
				Symbol symbol = peek(instruction);
				if(symbol != null && symbol instanceof Container) {
					int ordinal = (int)instruction.operand;
					frame.inters[ordinal] = ((Container)symbol).createInput();
				} else {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			case Instruction.TYPE_CLOSE_INTER_INPUT: {
				int ordinal = (int)instruction.operand;
				frame.inters[ordinal].closeInput();
				break;
			}
			
			case Instruction.TYPE_AS_INTER_OUTPUT: {
				Symbol symbol = peek(instruction);
				if(symbol != null && symbol instanceof Container) {
					int ordinal = (int)instruction.operand;
					frame.inters[ordinal] = ((Container)symbol).createOutput();
				} else {
					frame.i = instruction.falseJump;
					continue;
				}
				break;
			}
			case Instruction.TYPE_CLOSE_INTER_OUTPUT: {
				int ordinal = (int)instruction.operand;
				frame.inters[ordinal].closeOutput();
				break;
			}
			case Instruction.TYPE_REIFY_INTER: {
				int ordinal = (int)instruction.operand;
				put(instruction, frame.inters[ordinal].reify());
				break;
			}
			
			case Instruction.TYPE_QUOTE_OUTPUT_TARGET: {
				frame.output.put((Symbol)instruction.operand);
				break;
			}
			case Instruction.TYPE_QUOTE_OUTPUT_INTER: {
				frame.inters[instruction.outputOrdinal].put((Symbol)instruction.operand);
				break;
			}
			case Instruction.TYPE_QUOTE_OUTPUT_VARIABLE: {
				frame.variables[instruction.outputOrdinal] = (Symbol)instruction.operand;
				break;
			}
			case Instruction.TYPE_QUOTE_OUTPUT_MAP_SLOT: {
				((Symbol.Map)frame.variables[instruction.outputOrdinal]).put(instruction.outputSlotPath[0], (Symbol)instruction.operand);
				break;
			}
			
			case Instruction.TYPE_CHECK_POINT: {
				synchronized (this) {
					checkPoints++;
					
					if(checkPoints >= maxCheckPoints) {
						frame.i++;
						break finish;
					}
				}
			}
			}
			
			frame.i++;
		}
	}

	public static String resultToString(int result) {
		switch(result) {
		case Machine.RESULT_ABSENT:
			return "Absent";
		case Machine.RESULT_ACCEPTED:
			return "Accepted";
		case Machine.RESULT_REJECTED:
			return "Rejected";
		}
		
		return "<INVALID RESULT>";
	}
}
