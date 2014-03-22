package giki.parser;

import giki.runtime.CodeBuilder;

public class HLEntryPointBuilder implements HLCodeBuilder {
	private HLCodeBuilder body;

	public HLEntryPointBuilder(HLCodeBuilder body) {
		this.body = body;
	}

	@Override
	public void setup(Validation validation) {
		body.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		body.validate(validation);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		Object falseJumpLabel = codeBuilder.createLabel();
		codeBuilder.setFalseJump(falseJumpLabel);
		
		body.appendTo(codeBuilder);
		
		codeBuilder
			.acceptFinish()
			.label(falseJumpLabel)
			.rejectFinish();
	}
}
