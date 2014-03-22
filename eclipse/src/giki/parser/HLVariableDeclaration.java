package giki.parser;

import giki.runtime.CodeBuilder;

public class HLVariableDeclaration implements HLCodeBuilder {
	private String identifier;
	private HLCodeBuilder initialization;

	public HLVariableDeclaration(String identifier) {
		this(identifier, null);
	}

	public HLVariableDeclaration(String identifier, HLCodeBuilder initialization) {
		this.identifier = identifier;
		this.initialization = initialization;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		if(initialization != null) {
			codeBuilder.beginAssertion();
			codeBuilder.setOutputVariable(identifier);
			initialization.appendTo(codeBuilder);
			codeBuilder.endAssertion();
		}
	}

	@Override
	public void setup(Validation validation) {
		// Ensure variables are declared according a current default variable scope, such that naming conflicts are avoided across macros
		validation.declareVariable(identifier);
		
		if(initialization != null)
			initialization.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		if(initialization != null)
			initialization.validate(validation);
	}
}
