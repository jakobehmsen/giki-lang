package giki.parser;

import giki.runtime.CodeBuilder;
import giki.runtime.Instruction;
import giki.runtime.Symbol;

import java.util.ArrayList;

public class HLSequence implements HLCodeBuilder {
	private ArrayList<HLCodeBuilder> items;
	
	public HLSequence(ArrayList<HLCodeBuilder> items) {
		this.items = items;
	}
	
	public HLSequence() {
		items = new ArrayList<HLCodeBuilder>();
	}
	
	public HLSequence append(HLCodeBuilder element) {
		items.add(element);
		return this;
	}
	
	public HLSequence appendInstruction(int type) {
		return appendInstruction(type, null);
	}
	
	public HLSequence appendInstruction(int type, Object operand) {
		return append(new HLInstruction(type, operand));
	}
	
	public HLSequence move() {
		return appendInstruction(Instruction.TYPE_MOVE);
	}

	public HLCodeBuilder consume() {
		return appendInstruction(Instruction.TYPE_CONSUME);
	}
	
	public HLSequence peek() {
		return appendInstruction(Instruction.TYPE_PEEK);
	}
	
	public HLSequence quote(String identifier) {
		return quote(Symbol.getIdentifier(identifier));
	}
	
	public HLSequence quote(Symbol symbol) {
		return appendInstruction(Instruction.TYPE_QUOTE, symbol);
	}
	
	public HLSequence acceptFrame() {
		return appendInstruction(Instruction.TYPE_ACCEPT_FINISH);
	}
	
	public HLSequence rejectFrame() {
		return appendInstruction(Instruction.TYPE_REJECT_FINISH);
	}

	public HLSequence eq(String identifier) {
		return eq(Symbol.getIdentifier(identifier));
	}

	public HLSequence eq(Symbol symbol) {
		return appendInstruction(Instruction.TYPE_PEEK_EQUALS_CONSUME, symbol);
	}

	public HLSequence gte(Symbol symbol) {
		return appendInstruction(Instruction.TYPE_PEEK_GTE, symbol);
	}

	public HLSequence lte(Symbol symbol) {
		return appendInstruction(Instruction.TYPE_PEEK_LTE, symbol);
	}

	public HLSequence eof() {
		return appendInstruction(Instruction.TYPE_EOF);
	}

	public HLSequence setOutputTarget() {
		return append(HLSetOuputTarget.INSTANCE);
	}

	public HLSequence not(HLCodeBuilder expression) {
		return append(new HLNot(expression));
	}
	
	public HLSequence label(Object label) {
		return append(new HLLabel(label));
	}
	
	public HLSequence jump(Object label) {
		return append(new HLJump(label));
	}
	
	public HLSequence setFalseJump(Object label) {
		return append(new HLSetFalseJump(label));
	}

	public HLSequence loop(HLCodeBuilder body) {
		return append(new HLLoop(body));
	}

	public HLSequence setOutputVariable(String identifier) {
		return append(new HLSetOutputVariable(identifier));
	}

	public HLSequence lookupVariable(String identifier) {
		return append(new HLLookupVariable(identifier));
	}

	public HLSequence list(HLCodeBuilder initialization) {
		return append(new HLList(initialization));
	}

	@Override
	public void appendTo(CodeBuilder codeBuilder) {
		for(HLCodeBuilder element: items)
			element.appendTo(codeBuilder);
	}
	
	@Override
	public String toString() {
		if(items.size() == 1)
			return items.get(0).toString();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("(");
		
		for(int i = 0; i < items.size(); i++) {
			HLCodeBuilder item = items.get(i);
			if(i > 0)
				stringBuilder.append(" ");
			stringBuilder.append(item);
		}
		
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}

	@Override
	public void setup(Validation validation) {
		for(HLCodeBuilder item: items)
			item.setup(validation);
	}

	@Override
	public void validate(Validation validation) {
		for(HLCodeBuilder item: items)
			item.validate(validation);
	}

	public HLCodeBuilder getItem(int index) {
		return items.get(index);
	}
}