//package giki.parser;
//
//import java.util.ArrayList;
//
//import giki.runtime.CodeBuilder;
//import giki.runtime.ModuleLinker;
//import giki.runtime.Symbol;
//
//public class HLIdentifier implements HLCodeBuilder {
//	private static final int STATE_UNRESOLVED = 0;
//	private static final int STATE_VARIABLE = 1;
//	private static final int STATE_MODULE = 2;
//	
//	private int state;
//	private String identifier;
//	private ResourceLocation resourceLocation;
////	private ModuleLinker moduleLinker;
//	private ArrayList<ModuleLinker> moduleLinkers = new ArrayList<ModuleLinker>();
//
//	public HLIdentifier(String identifier, ResourceLocation resourceLocation) {
//		state = STATE_UNRESOLVED;
//		this.identifier = identifier;
//		this.resourceLocation = resourceLocation;
//	}
//	
//	public String getIdentifier() {
//		return identifier;
//	}
//	
//	public ResourceLocation getFileLocation() {
//		return resourceLocation;
//	}
//
//	@Override
//	public void appendTo(CodeBuilder codeBuilder) {
//		switch(state) {
//		case STATE_VARIABLE: {
//			codeBuilder.beginAssertion();
//			codeBuilder.setInputVariable(identifier);
//			codeBuilder.move();
//			codeBuilder.endAssertion();
//			break;
//		}
//		case STATE_MODULE: {
//			ModuleLinker moduleLinker = codeBuilder.pushFrame(resourceLocation, identifier);
//			moduleLinkers.add(moduleLinker);
//			break;
//		}
//		}
//	}
//
//	@Override
//	public void setup(Validation validation) { }
//
//	@Override
//	public void validate(Validation validation) {
//		if(validation.isVariable(identifier)) {
//			state = STATE_VARIABLE;
//		} else {
//			state = STATE_MODULE;
//			validation.addUnresolvedIdentifier(this);
//		}
//	}
//
//	public void setDepender(Module depender) {
//		for(ModuleLinker moduleLinker: moduleLinkers)
//			moduleLinker.link(depender);
//	}
//	
//	public void replace(Symbol astReplacement) {
//		// the placeholder of the current ast is set to hold astReplacement
//	}
//	
//	@Override
//	public String toString() {
//		return identifier;
//	}
//}
