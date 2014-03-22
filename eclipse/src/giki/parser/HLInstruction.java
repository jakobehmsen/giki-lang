package giki.parser;

import giki.runtime.CodeBuilder;

public class HLInstruction implements HLCodeBuilder {
	private final int type;
	private final Object operand;
	
	public HLInstruction(int type) {
		this(type, null);
	}
	
	public HLInstruction(int type, Object operand) {
		this.type = type;
		this.operand = operand;
	}
	
	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.appendInstruction(type, operand);
	}
	
	@Override
	public String toString() {
		String operandString = operand != null ? "(" + operand + ")" : "";
		return "§" + type + operandString;
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }

	public Object getOperand() {
		return operand;
	}
}