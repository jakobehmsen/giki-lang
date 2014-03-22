package giki.parser;

import giki.runtime.CodeBuilder;

public class HLFrom implements HLCodeBuilder {
	private HLCodeBuilder sourceExpression;
	private HLCodeBuilder body;

	public HLFrom(HLCodeBuilder sourceExpression, HLCodeBuilder body) {
		this.sourceExpression = sourceExpression;
		this.body = body;
	}

	@Override
	public void setup(Validation validation) {
		sourceExpression.setup(validation);
		body.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		sourceExpression.validate(validation);
		body.validate(validation);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object falseJumpLabel = codeBuilder.getFalseJumpLabel();
		
		Object labelAccept = codeBuilder.createLabel();
		Object labelReject = codeBuilder.createLabel();
		
		codeBuilder.setFalseJump(labelReject);
		
		Object inputScope = codeBuilder.createScope();
		codeBuilder.setOutputVariable(inputScope, "input");
		sourceExpression.appendTo(codeBuilder);
		codeBuilder.setInputVariable(inputScope, "input");
		Object interInput = codeBuilder.createInter();
		codeBuilder.asInterInput(interInput);

		codeBuilder.cleanupAssertionOutput();
		body.appendTo(codeBuilder);
		codeBuilder.jump(labelAccept);
		
		codeBuilder.label(labelReject);
		codeBuilder.closeInterInput(interInput);
		codeBuilder.jump(falseJumpLabel);
		
		codeBuilder.label(labelAccept);
		codeBuilder.closeInterInput(interInput);
		
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		return sourceExpression + " :>> " + body;
	}
}
