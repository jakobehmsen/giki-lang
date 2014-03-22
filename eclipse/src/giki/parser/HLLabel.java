package giki.parser;

import giki.runtime.CodeBuilder;

public class HLLabel implements HLCodeBuilder {
	private Object label;
	
	public HLLabel(Object label) {
		this.label = label;
	}
	
	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.label(label);
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }
}