package giki.runtime;

import giki.runtime.Machine.Frame;

public class Instruction {
	public static final int TYPE_QUOTE = 0;
	public static final int TYPE_ACCEPT_FINISH = 1;
	public static final int TYPE_REJECT_FINISH = 2;
	public static final int TYPE_ACCEPT_FRAME = 3;
	public static final int TYPE_REJECT_FRAME = 4;
	public static final int TYPE_PEEK = 5;
	public static final int TYPE_MOVE = 6;
	public static final int TYPE_CONSUME = 7;
	public static final int TYPE_JUMP = 8;
	public static final int TYPE_PIPE_START = 9;
	public static final int TYPE_PIPE_INTER = 10;
	public static final int TYPE_PIPE_END = 11;
	public static final int TYPE_SET_OUTPUT_NEW_INTER = 12;
	public static final int TYPE_PEEK_EQUALS_CONSUME = 13;
	public static final int TYPE_EOF = 14;
	public static final int TYPE_NEW_MAP = 15;
	public static final int TYPE_PUSH_FRAME = 16;
	public static final int TYPE_PEEK_GTE = 17;
	public static final int TYPE_PEEK_LTE = 18;
	public static final int TYPE_MAP_UNION = 19;
	public static final int TYPE_FILE = 20;
	public static final int TYPE_AS_INTER_INPUT = 21;
	public static final int TYPE_CLOSE_INTER_INPUT = 22;
	public static final int TYPE_AS_INTER_OUTPUT = 23;
	public static final int TYPE_CLOSE_INTER_OUTPUT = 24;
	public static final int TYPE_REIFY_INTER = 25;
	
	public static final int TYPE_CHECK_POINT = 128;
	
	public static final int TYPE_QUOTE_OUTPUT_TARGET = 256;
	public static final int TYPE_QUOTE_OUTPUT_INTER = 257;
	public static final int TYPE_QUOTE_OUTPUT_VARIABLE = 258;
	public static final int TYPE_QUOTE_OUTPUT_MAP_SLOT = 259;
	
	public final int type;
	public final Object operand;
	public final int inputType;
	public final int inputOrdinal;
	public final int[] inputSlotPath;
	public final int outputType;
	public final int outputOrdinal;
	public final int[] outputSlotPath;
	public final int falseJump;
	
	public Instruction(int type, Object operand, int inputType,
			int inputOrdinal, int[] inputSlotPath, int outputType, int outputOrdinal, int[] outputSlotPath, int falseJump) {
		this.type = type;
		this.operand = operand;
		this.inputType = inputType;
		this.inputOrdinal = inputOrdinal;
		this.inputSlotPath = inputSlotPath;
		this.outputType = outputType;
		this.outputOrdinal = outputOrdinal;
		this.outputSlotPath = outputSlotPath;
		this.falseJump = falseJump;
	}
	
	public int specializedType() {
		// TODO: Use reflection and cache result
		// Indicate specialized types by using rules for post fixes:
		// E.g. if post-fixed with _OUTPUT_TARGET, then that is the type to use for output
		// This will probably require mapping names each instruction.
		// Perhaps, there should be a canonical instruction class in which such data is contained.
		switch(type) {
		case Instruction.TYPE_QUOTE:
			switch(outputType) {
			case Frame.OUTPUT_TARGET:
				return TYPE_QUOTE_OUTPUT_TARGET;
			case Frame.OUTPUT_INTER:
				return TYPE_QUOTE_OUTPUT_INTER;
			case Frame.OUTPUT_VARIABLE:
				return TYPE_QUOTE_OUTPUT_VARIABLE;
			case Frame.OUTPUT_MAP_SLOT:
				return TYPE_QUOTE_OUTPUT_MAP_SLOT;
			}
			
			break;
		}
		
		return -1;
	}
	
	public Instruction specialized() {
		int specializedType = specializedType();
		
		if(specializedType != -1)
			return new Instruction(specializedType, operand, inputType, inputOrdinal, inputSlotPath, outputType, outputOrdinal, outputSlotPath, falseJump);
		
		return this;
	}
	
//	public static Instruction get(int type) {
//		return get(type, null);
//	}
//
//	public static Instruction get(int type, Object operand) {
//		return new Instruction(type, operand);
//	}
}