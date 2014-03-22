package giki.parser;

import giki.runtime.CodeBuilder;
import giki.runtime.Instruction;

import java.util.LinkedHashMap;
import java.util.Map;

public class HLMap implements HLCodeBuilder {
	private LinkedHashMap<String, HLCodeBuilder> slots = new LinkedHashMap<String, HLCodeBuilder>();

	public void setSlot(String identifier, HLCodeBuilder slotValue) {
		slots.put(identifier, slotValue);
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		codeBuilder.beginAssertion();
		
		Object scope = codeBuilder.createScope();
		codeBuilder.setOutputVariable(scope, "map");
		codeBuilder.appendInstruction(Instruction.TYPE_NEW_MAP);
		
		for(Map.Entry<String, HLCodeBuilder> slot: slots.entrySet()) {
			String identifier = slot.getKey();
			HLCodeBuilder value = slot.getValue();
			
			codeBuilder.setOutputMapSlot(scope, "map", identifier);
			value.appendTo(codeBuilder);
		}
		
		codeBuilder.cleanupAssertionOutput();
		codeBuilder.setInputVariable(scope, "map");
		codeBuilder.move();
		
		codeBuilder.endAssertion();
	}

	@Override
	public void setup(Validation validation) {
		for(Map.Entry<String, HLCodeBuilder> slot: slots.entrySet()) {
			HLCodeBuilder value = slot.getValue();
			value.setup(validation);
		}
	}

	@Override
	public void validate(Validation validation) {
		for(Map.Entry<String, HLCodeBuilder> slot: slots.entrySet()) {
			HLCodeBuilder value = slot.getValue();
			value.validate(validation);
		}
	}
	
	@Override
	public String toString() {
		return slots.toString();
	}
}
