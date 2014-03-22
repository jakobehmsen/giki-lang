package giki.parser;

import giki.runtime.CodeBuilder;

public class HLSetOuputTarget implements HLCodeBuilder {
	public static final HLSetOuputTarget INSTANCE = new HLSetOuputTarget();
	
	private HLSetOuputTarget() { }
	
	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.setOutputTarget();
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }
}
