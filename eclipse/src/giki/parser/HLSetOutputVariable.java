package giki.parser;

import giki.runtime.CodeBuilder;

public class HLSetOutputVariable implements HLCodeBuilder {
	private String identifier;

	public HLSetOutputVariable(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.setOutputVariable(identifier);
	}

	@Override
	public void setup(Validation validation) { }

	@Override
	public void validate(Validation validation) { 
		if(!validation.isVariable(identifier)) {
			// TODO: ?
		}
	}
}
