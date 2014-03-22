package giki.parser;


import giki.runtime.CodeBuilder;

import java.util.ArrayList;

public class HLDecision implements HLCodeBuilder {
	private ArrayList<HLCodeBuilder> alternatives;
	
	public HLDecision(ArrayList<HLCodeBuilder> alternatives) {
		this.alternatives = alternatives;
	}
	
	public HLDecision() {
		alternatives = new ArrayList<HLCodeBuilder>();
	}

	public HLDecision alternative(HLCodeBuilder alternative) {
		alternatives.add(alternative);
		return this;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object acceptLabel = codeBuilder.createLabel();
		Object rejectLabel = codeBuilder.getFalseJumpLabel();
		
		Object nextAlternativeLabel = null;
		for(int i = 0; i < alternatives.size(); i++) {
			if(i > 0)
				codeBuilder.label(nextAlternativeLabel);
			
			HLCodeBuilder alternative = alternatives.get(i);
//			codeBuilder.beginAssertion();
			
			nextAlternativeLabel = codeBuilder.createLabel();
			if(i < alternatives.size() - 1) {
				codeBuilder.setFalseJump(nextAlternativeLabel);
			} else {
				codeBuilder.setFalseJump(rejectLabel);
			}
			
			alternative.appendTo(codeBuilder);
			
			if(i < alternatives.size() - 1) {
				codeBuilder.jump(acceptLabel);
			}
			
//			codeBuilder.endAssertion();
		}
		
		codeBuilder.label(acceptLabel);
		
		codeBuilder.endAssertion();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < alternatives.size(); i++) {
			HLCodeBuilder pipe = alternatives.get(i);
			if(i > 0)
				stringBuilder.append(" | ");
			stringBuilder.append(pipe);
		}
		
		return stringBuilder.toString();
	}

	@Override
	public void setup(Validation validation) {
		for(HLCodeBuilder alternative: alternatives)
			alternative.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		for(HLCodeBuilder alternative: alternatives)
			alternative.validate(validation);
	}
}
