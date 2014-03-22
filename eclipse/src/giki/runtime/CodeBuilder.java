package giki.runtime;

import giki.parser.Module;
import giki.parser.ModuleApplication;
import giki.parser.ResourceLocation;
import giki.runtime.CodeBuilder.Build;
import giki.runtime.Machine.Frame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class CodeBuilder {
	public abstract static class Element {
		public static final int TYPE_DIRECT = 0;
		public static final int TYPE_JUMP = 1;
		public static final int TYPE_CALL = 2;
		public static final int TYPE_DIRECT_OPERAND_ORDINAL = 3;
		
		public final int type;
		
		public final int inputType;
		public final Object inputIdentifier;
		public final Object[] inputSlotPath;
		public final int outputType;
		public final Object outputIdentifier;
		public final Object[] outputSlotPath;
		public final Object falseJumpLabel;
		
		public Element(int type, AssertionInfo assertion) {
			this.type = type;
			this.inputType = assertion.inputType;
			this.inputIdentifier = assertion.inputIdentifier;
			this.inputSlotPath = assertion.inputSlotPath;
			this.outputType = assertion.outputType;
			this.outputIdentifier = assertion.outputIdentifier;
			this.outputSlotPath = assertion.outputSlotPath;
			this.falseJumpLabel = assertion.falseJumpLabel;
		}

		public static final class Direct extends Element {
			public final int type;
			public final Object operand;
			
			public Direct(AssertionInfo assertion, int type, Object operand) {
				super(TYPE_DIRECT, assertion);
				
				this.type = type;
				this.operand = operand;
			}
		}
		
		public static final class Jump extends Element {
			public final Object label;
			
			public Jump(AssertionInfo assertion, Object label) {
				super(TYPE_JUMP, assertion);
				
				this.label = label;
			}
		}
		
		public static final class Call extends Element implements ModuleLinker {
			public final ResourceLocation resourceLocation;
//			public final String moduleIdentifier;
			public final CodeBuilder.Build build;
			
			public Call(AssertionInfo assertion, ResourceLocation resourceLocation, CodeBuilder.Build build) {
				super(TYPE_CALL, assertion);
				
//				if(moduleIdentifier.equals("wschar"))
//					new String();
				
				this.resourceLocation = resourceLocation;
				this.build = build;
			}

			@Override
			public void link(Module module) {
//				if(module == null) {
//					new String();
//				}
//				this.module = module;
			}
		}
		
		public static final class DirectOperandOrdinal extends Element {
			public static final int ORDINAL_VARIABLE = 0;
			public static final int ORDINAL_INTER = 1;
			
			public final int type;
			public final Object identifier;
			public final int ordinalType;
			
			public DirectOperandOrdinal(AssertionInfo assertion, int type, Object identifier, int ordinalType) {
				super(TYPE_DIRECT_OPERAND_ORDINAL, assertion);
				
				this.type = type;
				this.identifier = identifier;
				this.ordinalType = ordinalType;
			}
		}
	}
	
	private ArrayList<CodeBuilder.Element> elements = new ArrayList<CodeBuilder.Element>();
	private int labelIndex;
	private Stack<AssertionInfo> assertionStack = new Stack<CodeBuilder.AssertionInfo>();
	private AssertionInfo assertion;
	
	public CodeBuilder() {
		assertion = new AssertionInfo(Machine.Frame.INPUT_SOURCE, -1, null, Machine.Frame.OUTPUT_TARGET, -1, null, null);
		assertionStack.push(assertion);
	}
	
	public Object createLabel() {
		return labelIndex++;
	}
	
	private int interIndex = 1;

	public Object createInter() {
		return interIndex++;
	}
	
	public CodeBuilder appendInstruction(int type) {
		return appendInstruction(type, null);
	}
	
	public CodeBuilder appendInstruction(int type, Object operand) {
		append(new Element.Direct(assertion, type, operand));
		
		return this;
	}

	public void setOutputTarget() {
		assertion.outputType = Frame.OUTPUT_TARGET;
		assertion.outputIdentifier = null;
		assertion.outputSlotPath = null;
	}

	public CodeBuilder pipeStart(Object identifier) {
		assertion.outputType = Frame.OUTPUT_INTER;
		assertion.outputIdentifier = identifier;
		assertion.outputSlotPath = null;

		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_PIPE_START, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
	}

	public CodeBuilder pipeInter(Object identifier) {
		assertion.inputType = Frame.INPUT_INTER;
		assertion.inputIdentifier = identifier;
		assertion.inputSlotPath = null;
		assertion.outputType = Frame.OUTPUT_INTER;
		assertion.outputIdentifier = identifier;
		assertion.outputSlotPath = null;
		
		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_PIPE_INTER, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
	}

	public CodeBuilder pipeEnd(Object identifier) {
		append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_PIPE_END, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));

		assertion.inputType = Frame.INPUT_INTER;
		assertion.inputIdentifier = identifier;
		assertion.inputSlotPath = null;
		assertion.outputType = assertion.initialOutputType;
		assertion.outputIdentifier = assertion.initialOutputIdentifier;
		assertion.outputSlotPath = assertion.initialOutputSlotPath;
		
		return this;
	}
	
	public CodeBuilder move() {
		return appendInstruction(Instruction.TYPE_MOVE);
	}
	
	public CodeBuilder peek() {
		return appendInstruction(Instruction.TYPE_PEEK);
	}
	
	public CodeBuilder quote(Symbol symbol) {
		return appendInstruction(Instruction.TYPE_QUOTE, symbol);
	}

	public CodeBuilder asInterInput(Object identifier) {
		append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_AS_INTER_INPUT, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
		
		return setInputInter(identifier);
	}

	public CodeBuilder closeInterInput(Object identifier) {
		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_CLOSE_INTER_INPUT, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
	}

	public CodeBuilder asInterOutput(Object identifier) {
		append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_AS_INTER_OUTPUT, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
		
		return setOutputInter(identifier, false);
	}

	public CodeBuilder closeInterOutput(Object identifier) {
		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_CLOSE_INTER_OUTPUT, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
	}

	public CodeBuilder interReify(Object identifier) {
		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_REIFY_INTER, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
	}
	
	public CodeBuilder acceptFrame() {
		return appendInstruction(Instruction.TYPE_ACCEPT_FRAME);
	}
	
	public CodeBuilder rejectFrame() {
		return appendInstruction(Instruction.TYPE_REJECT_FRAME);
	}
	
	public CodeBuilder acceptFinish() {
		return appendInstruction(Instruction.TYPE_ACCEPT_FINISH);
	}
	
	public CodeBuilder rejectFinish() {
		return appendInstruction(Instruction.TYPE_REJECT_FINISH);
	}
	
	public CodeBuilder setInputVariable(String identifier) {
		return setInputVariable(0, identifier);
	}

	public CodeBuilder setInputVariable(Object scope, String identifier) {
		return setInputVariableDirect("" + scope + "_" + identifier);
	}

	public CodeBuilder setInputVariableDirect(String identifier) {
		assertion.inputType = Frame.INPUT_VARIABLE;
		assertion.inputIdentifier = identifier;
		assertion.inputSlotPath = null;
		
		return this;
	}

	public void setInputMapSlot(Object scope, Object mapIdentifier, Object slotIdentifier) {
		assertion.inputType = Frame.INPUT_MAP_SLOT;
		assertion.inputIdentifier = "" + scope + "_" + mapIdentifier;
		assertion.inputSlotPath = new Object[]{slotIdentifier};
	}
	
	public CodeBuilder setOutputVariable(String identifier) {
		return setOutputVariable(0, identifier);
	}

	public CodeBuilder setOutputVariable(Object scope, String identifier) {
		return setOutputVariableDirect("" + scope + "_" + identifier);
	}

	public CodeBuilder setOutputVariableDirect(String identifier) {
		assertion.outputType = Frame.OUTPUT_VARIABLE;
		assertion.outputIdentifier = identifier;
		assertion.outputSlotPath = null;
		
		return this;
	}

//	public CodeBuilder lookupVariable(String identifier) {
//		return lookupVariable(0, identifier);
//	}
//
//	public CodeBuilder lookupVariable(Object scope, String identifier) {
//		identifier = "" + scope + "_" + identifier;
//
//		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_LOOKUP_VARIABLE, identifier, Element.DirectOperandOrdinal.ORDINAL_VARIABLE));
//	}

	public void setOutputMapSlot(Object scope, Object mapIdentifier, Object slotIdentifier) {
		assertion.outputType = Frame.OUTPUT_MAP_SLOT;
		assertion.outputIdentifier = "" + scope + "_" + mapIdentifier;
		assertion.outputSlotPath = new Object[]{slotIdentifier};
	}
	
	private int scope;

	public Object createScope() {
		return scope++;
	}

	public CodeBuilder setInputInter(Object identifier) {
		assertion.inputType = Frame.INPUT_INTER;
		assertion.inputIdentifier = identifier;
		assertion.inputSlotPath = null;
		
		return this;
	}

//	public CodeBuilder setInputInterReify(Object identifier) {
//		assertion.inputType = Frame.INPUT_INTER_REIFY;
//		assertion.inputIdentifier = identifier;
//		assertion.inputSlotPath = null;
//		
//		return this;
//	}

	public CodeBuilder setOutputInter(Object identifier, boolean create) {
		assertion.outputType = Frame.OUTPUT_INTER;
		assertion.outputIdentifier = identifier;
		assertion.outputSlotPath = null;
		
		if(create)
			return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_SET_OUTPUT_NEW_INTER, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
		else
			return this;
	}

//	public CodeBuilder lookupInter(Object identifier) {
//		return append(new Element.DirectOperandOrdinal(assertion, Instruction.TYPE_LOOKUP_INTER, identifier, Element.DirectOperandOrdinal.ORDINAL_INTER));
//	}
	
	private Hashtable<Object, Integer> labelToIndexMap = new Hashtable<Object, Integer>();
	
	public CodeBuilder label(Object label)
	{
		int index = elements.size();;
		
		labelToIndexMap.put(label, index);
		
		return this;
	}
	
	public CodeBuilder jump(Object label)
	{
		append(new Element.Jump(assertion, label));
		
		return this;
	}
	
	public CodeBuilder setFalseJump(Object label) {
		assertion.falseJumpLabel = label;
		
		return this;
	}
	
	private CodeBuilder append(Element element) {
		elements.add(element);
		return this;
	}

	public Object getFalseJumpLabel() {
		return assertion.falseJumpLabel;
	}
	
	public static class Build {
		public final Instruction[] code;
		public final int variableCount;
		public final int interCount;
		
		public Build(Instruction[] code, int variableCount, int interCount) {
			this.code = code;
			this.variableCount = variableCount;
			this.interCount = interCount;
		}
	}
	
	private static int identifierToOrdinal(Hashtable<Object, Integer> identifierToOrdinalMap, Object identifier) {
		Integer inputOrdinalObj = identifierToOrdinalMap.get(identifier);
		if(inputOrdinalObj == null) {
			inputOrdinalObj = identifierToOrdinalMap.size();
			identifierToOrdinalMap.put(identifier, inputOrdinalObj);
		}
		return inputOrdinalObj;
	}
	
	// TODO: Consider: Perhaps the isEntryPoint field should be transfered to build as a parameter, such that arguments for build indicates whether
	// the build should be an entry point or not. This probably requires two new types of element: Element.AcceptFrame and Element.RejectFrame, which
	// is interpreted during a call to build according to the supplied isEntryPoint argument.
	public Build build() {
		Hashtable<Object, Integer> variableIdentifierToOrdinalMap = new Hashtable<Object, Integer>();
		Hashtable<Object, Integer> interIdentifierToOrdinalMap = new Hashtable<Object, Integer>();
		
		ArrayList<Instruction> instructionsBuilder = new ArrayList<Instruction>();
		
		for(Element element: elements) {
			int inputType = element.inputType;
			int inputOrdinal;
			int[] inputSlotPath;
			
			switch(inputType) {
			case Machine.Frame.INPUT_INTER: {
				inputOrdinal = identifierToOrdinal(interIdentifierToOrdinalMap, element.inputIdentifier);
				inputSlotPath = null;
				
				break;
			}
			case Machine.Frame.INPUT_VARIABLE: {
				inputOrdinal = identifierToOrdinal(variableIdentifierToOrdinalMap, element.inputIdentifier);
				inputSlotPath = null;
				
				break;
			}
			case Machine.Frame.INPUT_MAP_SLOT: {
				inputOrdinal = identifierToOrdinal(variableIdentifierToOrdinalMap, element.inputIdentifier);
				
				inputSlotPath = new int[element.inputSlotPath.length];
				for(int i = 0; i < element.inputSlotPath.length; i++) {
					Object inputSlotIdentifier = element.inputSlotPath[i];
					int inputSlotSymbolCode = SymbolTable.getSymbolCode((String)inputSlotIdentifier);
					inputSlotPath[i] = inputSlotSymbolCode;
				}
				
				break;
			}
			default: {
				inputOrdinal = -1;
				inputSlotPath = null;
				
				break;
			}
			}
			
			int outputType = element.outputType;
			int outPutOrdinal;
			int[] outputSlotPath;
			
			switch(outputType) {
			case Machine.Frame.OUTPUT_INTER: {
				outPutOrdinal = identifierToOrdinal(interIdentifierToOrdinalMap, element.outputIdentifier);
				outputSlotPath = null;
				
				break;
			}
			case Machine.Frame.OUTPUT_VARIABLE: {
				outPutOrdinal = identifierToOrdinal(variableIdentifierToOrdinalMap, element.outputIdentifier);
				outputSlotPath = null;
				
				break;
			}
			case Machine.Frame.OUTPUT_MAP_SLOT: {
				outPutOrdinal = identifierToOrdinal(variableIdentifierToOrdinalMap, element.outputIdentifier);
				
				outputSlotPath = new int[element.outputSlotPath.length];
				for(int i = 0; i < element.outputSlotPath.length; i++) {
					Object outputSlotIdentifier = element.outputSlotPath[i];
					int outputSlotSymbolCode = SymbolTable.getSymbolCode((String)outputSlotIdentifier);
					outputSlotPath[i] = outputSlotSymbolCode;
				}
				
				break;
			}
			default: {
				outPutOrdinal = -1;
				outputSlotPath = null;
				
				break;
			}
			}
			
			int falseJump = labelToIndexMap.get(element.falseJumpLabel);
			
			if(falseJump >= elements.size())
				new String();
			
			int instructionType;
			Object instructionOperand;
			
			switch(element.type) {
			case Element.TYPE_DIRECT: {
				instructionType = ((Element.Direct)element).type;
				instructionOperand = ((Element.Direct)element).operand;
				break;
			}
			case Element.TYPE_JUMP: {
				int index = labelToIndexMap.get(((Element.Jump)element).label);
				
				instructionType = Instruction.TYPE_JUMP;
				instructionOperand = index;

				break;
			} 
			case Element.TYPE_CALL: {
				instructionType = Instruction.TYPE_PUSH_FRAME;
				// TODO: Should be resolved ahead of code generation time - specifically, during interim AST processing
//				instructionOperand = moduleApplication.getModuleBuild(false, ((Element.Call)element).resourceLocation, ((Element.Call)element).moduleIdentifier);
				instructionOperand = ((Element.Call)element).build;
				
				break;
			}
			case Element.TYPE_DIRECT_OPERAND_ORDINAL: {
				Element.DirectOperandOrdinal directOperandOrdinalElement = (Element.DirectOperandOrdinal)element;
				
				instructionType = directOperandOrdinalElement.type;
				
				switch(directOperandOrdinalElement.ordinalType) {
				case Element.DirectOperandOrdinal.ORDINAL_VARIABLE:
					instructionOperand = identifierToOrdinal(variableIdentifierToOrdinalMap, directOperandOrdinalElement.identifier);
					break;
				case Element.DirectOperandOrdinal.ORDINAL_INTER:
					instructionOperand = identifierToOrdinal(interIdentifierToOrdinalMap, directOperandOrdinalElement.identifier);
					break;
				default:
					instructionOperand = null;
					break;
				}
				
				break;
			}
			default: {
				instructionType = -1;
				instructionOperand = null;
			}
			}
			
			Instruction instruction = new Instruction(instructionType, instructionOperand, inputType, inputOrdinal, inputSlotPath, outputType, outPutOrdinal, outputSlotPath, falseJump);
			
			instruction = instruction.specialized();
			
			instructionsBuilder.add(instruction);
		}

		Instruction[] instructions = new Instruction[instructionsBuilder.size()];
		instructionsBuilder.toArray(instructions);
		
		return new Build(instructions, variableIdentifierToOrdinalMap.size(), interIdentifierToOrdinalMap.size());
	}
	
	private static class AssertionInfo {
		public final int initialInputType;
		public final Object initialInputIdentifier;
		public final Object[] initialInputSlotPath;
		public final int initialOutputType;
		public final Object initialOutputIdentifier;
		public final Object[] initialOutputSlotPath;
		public final Object initialFalseJumpLabel;

		public int inputType;
		public Object inputIdentifier;
		public Object[] inputSlotPath;
		public int outputType;
		public Object outputIdentifier;
		public Object[] outputSlotPath;
		public Object falseJumpLabel;
		
		public AssertionInfo(int inputType, Object inputIdentifier, Object[] inputSlotPath, int outputType, Object outputIdentifier, Object[] outputSlotPath, Object falseJumpLabel) {
			this.initialInputType = inputType;
			this.initialInputIdentifier = inputIdentifier;
			this.initialInputSlotPath = inputSlotPath;
			this.initialOutputType = outputType;
			this.initialOutputIdentifier = outputIdentifier;
			this.initialOutputSlotPath = outputSlotPath;
			this.initialFalseJumpLabel = falseJumpLabel;
			
			this.inputType = inputType;
			this.inputIdentifier = inputIdentifier;
			this.inputSlotPath = inputSlotPath;
			this.outputType = outputType;
			this.outputIdentifier = outputIdentifier;
			this.outputSlotPath = outputSlotPath;
			this.falseJumpLabel = falseJumpLabel;
		}
	}
	
	public void beginAssertion() {
		assertion = new AssertionInfo(
			assertion.inputType, assertion.inputIdentifier, assertion.inputSlotPath, assertion.outputType, assertion.outputIdentifier, assertion.outputSlotPath, assertion.falseJumpLabel);
		assertionStack.add(assertion);
		
//		System.out.println("begin assertion: input=" + inputToString(assertion) + ", output=" + outputToString(assertion));
	}
	
	public void endAssertion() {
		cleanupAssertion();
		assertionStack.pop();
		assertion = assertionStack.peek();
		
//		System.out.println("end assertion: input=" + inputToString(assertion) + ", output=" + outputToString(assertion));
	}
	
	private static String inputToString(AssertionInfo assertionInfo) {
		switch(assertionInfo.inputType) {
		case Machine.Frame.INPUT_SOURCE:
			return "<input source>";
		case Machine.Frame.INPUT_INTER:
			return "<input inter: " + assertionInfo.inputIdentifier + ">";
		}
		return null;
	}
	
	private static String outputToString(AssertionInfo assertionInfo) {
		switch(assertionInfo.outputType) {
		case Machine.Frame.OUTPUT_TARGET:
			return "<output target>";
		case Machine.Frame.OUTPUT_INTER:
			return "<output inter: " + assertionInfo.outputIdentifier + ">";
		case Machine.Frame.OUTPUT_VARIABLE:
			return "<output variable: " + assertionInfo.outputIdentifier + ">";
		}
		return null;
	}
	
	public void cleanupAssertion() {
		assertion.inputType = assertion.initialInputType;
		assertion.inputIdentifier = assertion.initialInputIdentifier;
		assertion.inputSlotPath = assertion.initialInputSlotPath;
		assertion.outputType = assertion.initialOutputType;
		assertion.outputIdentifier = assertion.initialOutputIdentifier;
		assertion.outputSlotPath = assertion.initialOutputSlotPath;
		assertion.falseJumpLabel = assertion.initialFalseJumpLabel;
	}
	
	public void cleanupAssertionInput() {
		assertion.inputType = assertion.initialInputType;
		assertion.inputIdentifier = assertion.initialInputIdentifier;
		assertion.inputSlotPath = assertion.initialInputSlotPath;
	}
	
	public void cleanupAssertionOutput() {
		assertion.outputType = assertion.initialOutputType;
		assertion.outputIdentifier = assertion.initialOutputIdentifier;
		assertion.outputSlotPath = assertion.initialOutputSlotPath;
	}

	public ModuleLinker pushFrame(ResourceLocation resourceLocation, CodeBuilder.Build build) {
		Element.Call call = new Element.Call(assertion, resourceLocation, build);
		
		append(call);
		
		return call;
	}

	public void checkPoint() {
		appendInstruction(Instruction.TYPE_CHECK_POINT);
	}
	
	private Stack<Integer> ioScopeIndexStack = new Stack<Integer>();

	public void beginIOScope() {
		beginAssertion();
//		ioScopeStack.add(assertion);
//		assertion = new AssertionInfo(
//			assertion.inputType, assertion.inputIdentifier, assertion.inputSlotPath, assertion.outputType, assertion.outputIdentifier, assertion.outputSlotPath, assertion.falseJumpLabel);
//		assertionStack.add(assertion);
//		
//		System.out.println("begin assertion: input=" + inputToString(assertion) + ", output=" + outputToString(assertion));
		
		ioScopeIndexStack.push(assertionStack.size());
	}

	public void useIOScope(int ioScope) {
		int ioScopeIndex = ioScopeIndexStack.get(ioScopeIndexStack.size() - 1 - ioScope);
		AssertionInfo ioAssertion = assertionStack.get(ioScopeIndex);
//		assertion = new AssertionInfo(
//			ioAssertion.inputType, ioAssertion.inputIdentifier, ioAssertion.inputSlotPath, ioAssertion.outputType, ioAssertion.outputIdentifier, ioAssertion.outputSlotPath, assertion.falseJumpLabel);
		// Input only
		assertion = new AssertionInfo(
			ioAssertion.inputType, ioAssertion.inputIdentifier, ioAssertion.inputSlotPath, assertion.outputType, assertion.outputIdentifier, assertion.outputSlotPath, assertion.falseJumpLabel);
		assertionStack.add(assertion);
		ioScopeIndexStack.push(assertionStack.size() - 1);
	}

	public void endIOScope() {
		ioScopeIndexStack.pop();
		endAssertion();
	}
 }
