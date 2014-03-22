package giki.parser;

import giki.runtime.CodeBuilder;

public class HLVariableUsage implements HLCodeBuilder {
	private String identifier;

	public HLVariableUsage(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public void setup(Validation validation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Validation validation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		codeBuilder.setInputVariable(identifier);
		codeBuilder.move();
		codeBuilder.endAssertion();
	}

}
