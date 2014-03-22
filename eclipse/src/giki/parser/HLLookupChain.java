package giki.parser;

import giki.runtime.CodeBuilder;

import java.util.ArrayList;

public class HLLookupChain implements HLCodeBuilder {
	private HLCodeBuilder term;
	private ArrayList<String> slotIdentifiers;
	
	public HLLookupChain(HLCodeBuilder term, ArrayList<String> slotIdentifiers) {
		this.term = term;
		this.slotIdentifiers = slotIdentifiers;
	}
	
	public HLLookupChain(HLCodeBuilder term) {
		this.term = term;
		slotIdentifiers = new ArrayList<String>();
	}

	public void append(String slotIdentifier) {
		slotIdentifiers.add(slotIdentifier);
	}

	@Override
	public void setup(Validation validation) { 
		term.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		term.validate(validation);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object scope = codeBuilder.createScope();
		codeBuilder.setOutputVariable(scope, "term");
		term.appendTo(codeBuilder);
		
		for(String slotIdentifier: slotIdentifiers) {
			codeBuilder.setInputMapSlot(scope, "term", slotIdentifier);
			codeBuilder.move();
		}
		
		codeBuilder.cleanupAssertionOutput();
		codeBuilder.setInputVariable(scope, "term");
		codeBuilder.move();
		
		codeBuilder.endAssertion();
	}
}
