package giki.parser;

import giki.runtime.CodeBuilder;

public class HLIOScope implements HLCodeBuilder {
	private HLCodeBuilder expr;
	
	public HLIOScope(HLCodeBuilder expr) {
		this.expr = expr;
	}

	@Override
	public void setup(Validation validation) {
		expr.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		expr.validate(validation);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginIOScope();
		expr.appendTo(codeBuilder);
		codeBuilder.endIOScope();
	}

}
