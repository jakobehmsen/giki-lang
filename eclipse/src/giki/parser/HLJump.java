package giki.parser;

import giki.runtime.CodeBuilder;

public class HLJump implements HLCodeBuilder {
	private Object label;
	
	public HLJump(Object label) {
		this.label = label;
	}
	
	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.jump(label);
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }
}