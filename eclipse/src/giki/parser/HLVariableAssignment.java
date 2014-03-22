package giki.parser;

import giki.runtime.CodeBuilder;

public class HLVariableAssignment implements HLCodeBuilder {
	private String identifier;
	private HLCodeBuilder value;

	public HLVariableAssignment(String identifier, HLCodeBuilder value) {
		this.identifier = identifier;
		this.value = value;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		codeBuilder.setOutputVariable(identifier);
		value.appendTo(codeBuilder);
		codeBuilder.endAssertion();
	}

	@Override
	public void setup(Validation validation) {
		// Ensure variables are declared according a current default variable scope, such that naming conflicts are avoided across macros
		value.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		value.validate(validation);
	}
}
