package giki.parser;

import giki.runtime.CodeBuilder;

public class HLLookupVariable implements HLCodeBuilder {
	private String identifier;

	public HLLookupVariable(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		codeBuilder.setInputVariable(identifier);
		codeBuilder.move();
		codeBuilder.endAssertion();
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { }
}
