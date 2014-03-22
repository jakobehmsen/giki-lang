package giki.parser;

import giki.runtime.CodeBuilder;

public class HLNot implements HLCodeBuilder {
	private HLCodeBuilder expression;

	public HLNot(HLCodeBuilder expression) {
		this.expression = expression;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object notLabel = codeBuilder.createLabel();
		Object notNotLabel = codeBuilder.getFalseJumpLabel();
		
		codeBuilder.setFalseJump(notLabel);
		expression.appendTo(codeBuilder);
		codeBuilder.cleanupAssertion();
		codeBuilder.jump(notNotLabel);
		
		codeBuilder.label(notLabel);
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		return "!" + expression;
	}

	@Override
	public void setup(Validation validation) {
		expression.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		expression.validate(validation);
	}
}
