package giki.parser;

import giki.parser.HLCodeBuilder.Validation;
import giki.runtime.CodeBuilder;

public class HLIOScopeUsage implements HLCodeBuilder {
	private HLCodeBuilder expr;
	private int ioScope;

	public HLIOScopeUsage(HLCodeBuilder expr, int ioScope) {
		this.expr = expr;
		this.ioScope = ioScope;
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
		codeBuilder.useIOScope(ioScope);
		expr.appendTo(codeBuilder);
		codeBuilder.endIOScope();
	}
}
