package giki.parser;

import giki.runtime.CodeBuilder;

public class HLCall implements HLCodeBuilder {
	private CodeBuilder.Build build;

	public HLCall(CodeBuilder.Build build) {
		this.build = build;
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
		codeBuilder.pushFrame(null, build);
	}
}
