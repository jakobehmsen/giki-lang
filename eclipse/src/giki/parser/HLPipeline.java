package giki.parser;

import giki.runtime.CodeBuilder;

import java.util.ArrayList;

public class HLPipeline implements HLCodeBuilder {
//	private HLCodeBuilder from;
//	private HLCodeBuilder to;
	private ArrayList<HLCodeBuilder> pipes;
	
	public HLPipeline(ArrayList<HLCodeBuilder> pipes) {
		this.pipes = pipes;
	}
	
	public HLPipeline() {
		pipes = new ArrayList<HLCodeBuilder>();
	}
	
	public HLPipeline append(HLCodeBuilder pipe) {
		pipes.add(pipe);
		return this;
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
//		if(from != null && to != null) {
//		} else if(from != null) {
//			if(pipes.size() > 0) {
//				codeBuilder.beginAssertion();
//				
//				Object inter = codeBuilder.createInter();
//				
//				Object inputScope = codeBuilder.createScope();
//				codeBuilder.setOutputVariable(inputScope, "input");
//				from.appendTo(codeBuilder);
//				codeBuilder.setInputVariable(inputScope, "input");
//				Object interInput = codeBuilder.createInter();
//				codeBuilder.asInterInput(interInput);
//				
//				if(pipes.size() > 1) {
//					codeBuilder.pipeStart(inter);
//					pipes.get(0).appendTo(codeBuilder);
//					codeBuilder.closeInterInput(interInput);
//				
//					for(int i = 1; i < pipes.size() - 1; i++) {
//						codeBuilder.pipeInter(inter);
//						pipes.get(i).appendTo(codeBuilder);
//					}
//					
//					codeBuilder.pipeEnd(inter);
//					pipes.get(pipes.size() - 1).appendTo(codeBuilder);
//				} else {
//					codeBuilder.cleanupAssertionOutput();
//					pipes.get(0).appendTo(codeBuilder);
//					codeBuilder.closeInterInput(interInput);
//				}
//				
//				codeBuilder.endAssertion();
//			}
//		} else if(to != null) {
//			if(pipes.size() > 0) {
//				codeBuilder.beginAssertion();
//				
//				Object inter = codeBuilder.createInter();
//				
////				Object scope = codeBuilder.createScope();
////				codeBuilder.setOutputVariable(scope, "input");
////				from.appendTo(codeBuilder);
////				codeBuilder.setInputVariable(scope, "input");
////				Object interInput = codeBuilder.createInter();
////				codeBuilder.asInterInput(interInput);
//				codeBuilder.pipeStart(inter);
//				pipes.get(0).appendTo(codeBuilder);
//				
//				if(pipes.size() > 1) {
//					for(int i = 1; i < pipes.size() - 1; i++) {
//						codeBuilder.pipeInter(inter);
//						pipes.get(i).appendTo(codeBuilder);
//					}
//					
//					codeBuilder.pipeEnd(inter);
//					pipes.get(pipes.size() - 1).appendTo(codeBuilder);
//				} else {
//					codeBuilder.cleanupAssertionOutput();
//					pipes.get(0).appendTo(codeBuilder);
//				}
//				
//				codeBuilder.endAssertion();
//			}
//		} else {
//			if(pipes.size() > 1) {
//				codeBuilder.beginAssertion();
//				
//				Object inter = codeBuilder.createInter();
//				
//				codeBuilder.pipeStart(inter);
//				pipes.get(0).appendTo(codeBuilder);
//				
//				for(int i = 1; i < pipes.size() - 1; i++) {
//					codeBuilder.pipeInter(inter);
//							
//					pipes.get(i).appendTo(codeBuilder);
//				}
//
//				codeBuilder.pipeEnd(inter);
//				pipes.get(pipes.size() - 1).appendTo(codeBuilder);
//				
//				codeBuilder.endAssertion();
//			} else {
//				pipes.get(0).appendTo(codeBuilder);
//			}
//		}
		
		if(pipes.size() > 1) {
			codeBuilder.beginAssertion();
			
			Object inter = codeBuilder.createInter();
			
			codeBuilder.pipeStart(inter);
			pipes.get(0).appendTo(codeBuilder);
			
			for(int i = 1; i < pipes.size() - 1; i++) {
				codeBuilder.pipeInter(inter);
						
				pipes.get(i).appendTo(codeBuilder);
			}

			codeBuilder.pipeEnd(inter);
			pipes.get(pipes.size() - 1).appendTo(codeBuilder);
			
			codeBuilder.endAssertion();
		} else {
			pipes.get(0).appendTo(codeBuilder);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < pipes.size(); i++) {
			HLCodeBuilder pipe = pipes.get(i);
			if(i > 0)
				stringBuilder.append(" >> ");
			stringBuilder.append(pipe);
		}
		
		return stringBuilder.toString();
	}

	@Override
	public void setup(Validation validation) {
//		if(from != null)
//			from.setup(validation);
		for(HLCodeBuilder pipe: pipes)
			pipe.setup(validation);
//		if(to != null)
//			to.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
//		if(from != null)
//			from.validate(validation);
		for(HLCodeBuilder pipe: pipes)
			pipe.validate(validation);
//		if(to != null)
//			to.validate(validation);
	}

//	public void setFrom(HLCodeBuilder from) {
//		this.from = from;
//	}
//
//	public void setTo(HLCodeBuilder to) {
//		this.to = to;
//	}
}