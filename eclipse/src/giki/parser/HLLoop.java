package giki.parser;

import giki.runtime.CodeBuilder;

public class HLLoop implements HLCodeBuilder {
	private HLCodeBuilder body;

	public HLLoop(HLCodeBuilder body) {
		this.body = body;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		int loopUnrollCount = 20;
		
		Object loopLabel = codeBuilder.createLabel();
		Object endLabel = codeBuilder.createLabel();

		codeBuilder.beginAssertion();
		
		codeBuilder.setFalseJump(endLabel);
		codeBuilder.label(loopLabel);
		codeBuilder.checkPoint();
		
		for(int i = 0; i < loopUnrollCount; i++)
			body.appendTo(codeBuilder);
		
		codeBuilder.jump(loopLabel);
		codeBuilder.label(endLabel);
		
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		return body + "*";
	}

	@Override
	public void setup(Validation validation) {
		body.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		body.validate(validation);
	}
}