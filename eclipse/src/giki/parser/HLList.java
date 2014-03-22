package giki.parser;

import giki.runtime.CodeBuilder;

public class HLList implements HLCodeBuilder {
	private HLCodeBuilder initialization;
	
	public HLList(HLCodeBuilder initialization) {
		this.initialization = initialization;
	}
	
	public HLList() {
		this.initialization = new HLSequence();
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object listInter = codeBuilder.createInter();
		codeBuilder.setOutputInter(listInter, true);
		
		initialization.appendTo(codeBuilder);
		
		codeBuilder.cleanupAssertionOutput();
		codeBuilder.interReify(listInter);
		
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		return "[" + initialization + "]";
	}

	@Override
	public void setup(Validation validation) {
		initialization.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		initialization.validate(validation);
	}
}
