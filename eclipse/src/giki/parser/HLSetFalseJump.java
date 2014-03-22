package giki.parser;

import giki.runtime.CodeBuilder;

public class HLSetFalseJump implements HLCodeBuilder {
	private Object label;
	
	public HLSetFalseJump(Object label) {
		this.label = label;
	}
	
	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.setFalseJump(label);
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }
}