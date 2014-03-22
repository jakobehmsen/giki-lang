package giki.parser;

import giki.runtime.CodeBuilder;

public class HLTo implements HLCodeBuilder {
	private HLCodeBuilder targetExpression;
	private HLCodeBuilder body;

	public HLTo(HLCodeBuilder targetExpression, HLCodeBuilder body) {
		this.targetExpression = targetExpression;
		this.body = body;
	}

	@Override
	public void setup(Validation validation) {
		targetExpression.setup(validation);
		body.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		targetExpression.validate(validation);
		body.validate(validation);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object falseJumpLabel = codeBuilder.getFalseJumpLabel();
		
		Object labelAccept = codeBuilder.createLabel();
		Object labelReject = codeBuilder.createLabel();
		
		codeBuilder.setFalseJump(labelReject);
		
		Object outputScope = codeBuilder.createScope();
		codeBuilder.setOutputVariable(outputScope, "output");
		targetExpression.appendTo(codeBuilder);
		codeBuilder.setInputVariable(outputScope, "output");
		Object interOutput = codeBuilder.createInter();
		codeBuilder.asInterOutput(interOutput);

		codeBuilder.cleanupAssertionInput();
		body.appendTo(codeBuilder);
		codeBuilder.jump(labelAccept);
		
		codeBuilder.label(labelReject);
		codeBuilder.closeInterOutput(interOutput);
		codeBuilder.jump(falseJumpLabel);
		
		codeBuilder.label(labelAccept);
		codeBuilder.closeInterOutput(interOutput);
		
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		return body + " >>: " + targetExpression;
	}
}
