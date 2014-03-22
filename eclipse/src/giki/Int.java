package giki;

public class Int extends Idag {
	public static final int OPCODE_POP = 0;
	public static final int OPCODE_PUSH = 1;
	public static final int OPCODE_SWAP = 2;
	public static final int OPCODE_PUSH_HOST = 3;
	public static final int OPCODE_PUSH_RECEIVER = 4;
	public static final int OPCODE_RETURN = 5;
	public static final int OPCODE_ENQUIRE = 6;
	public static final int OPCODE_NEW = 7;
	public static final int OPCODE_NEW_LIST = 8;
	
	public static final int OPCODE_AGENT_SEND = 15;
	public static final int OPCODE_AGENT_DEFINE = 16;
	public static final int OPCODE_AGENT_LOOKUP = 17;
	
	public static final int OPCODE_INTS_ADD = 31;
	public static final int OPCODE_INTS_SUB = 32;
	public static final int OPCODE_INTS_MUL = 33;
	public static final int OPCODE_INTS_DIV = 34;

	public static final int TERM_HOST = 0;
	
	public final int value;
	
	private Int(int code) {
		super(Host.INSTANCE);
		this.value = code;
	}
	
	public static Int get(int code) {
		return new Int(code);
	}
	
	@Override
	public String toString() {
		return "" + value;
	}
}
