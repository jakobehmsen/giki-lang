package giki.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import giki.parser.HLCodeBuilder;
import giki.parser.HLEntryPointBuilder;
import giki.parser.HLPipeline;
import giki.parser.Module;
import giki.parser.ParseMessage;
import giki.parser.Parser;
import giki.parser.Resource;
import giki.parser.ResourceStore;
import giki.parser.Parser.ParseContext;
import giki.runtime.CodeBuilder;
import giki.runtime.IOBuffer;
import giki.runtime.Input;
import giki.runtime.Machine;
import giki.runtime.Symbol;
import giki.runtime.CodeBuilder.Build;

public class ModuleResolver {
	private Hashtable<String, Module> identifierToModuleMap = new Hashtable<String, Module>();
	private ArrayList<Module> modules = new ArrayList<Module>();
	private ArrayList<Module> modulesNeedResolution = new ArrayList<Module>();
	
	public ArrayList<ParseMessage> getParseMessages() {
		ArrayList<ParseMessage> parseMessages = new ArrayList<ParseMessage>();

		for(Module module: modules)
			parseMessages.addAll(module.parseContext.getMessages());
		
		return parseMessages;
	}
	
	public void addModules(ResourceStore resourceStore) throws IOException {
		for(Resource resource: resourceStore.getResources())
			addModule(resource);
	}
	
	public void addModule(Resource resource) throws IOException {
		Parser parser = new Parser(System.out);
		Module module = parser.toModule(resource);
		addModule(module);
	}
	
	public void addModule(Module module) {
		modules.add(module);
		if(module.parseContext.unresolvedDependency().size() > 0)
			modulesNeedResolution.add(module);
		
		identifierToModuleMap.put(module.path + module.name, module);
		if(!identifierToModuleMap.contains(module.name))
			identifierToModuleMap.put(module.name, module);
		
		for(Module innerModule: module.innerModules)
			addModule(innerModule);
	}
	
	public Module getModule(String identifier) {
		return identifierToModuleMap.get(identifier);
	}
	
	public static class ModuleExpansion {
		public Module module;
		public Symbol ast;
		public Parser.ParseContext parseContext = new ParseContext();
		public ArrayList<Module.Modifier> missingModifiers = new ArrayList<Module.Modifier>();
		
		public ArrayList<ParseMessage> getMessages() {
			ArrayList<ParseMessage> messages = new ArrayList<ParseMessage>();
			
			messages.addAll(parseContext.getMessages());
			
			for(Module.Modifier missingModifier: missingModifiers)
				messages.add(new ParseMessage(missingModifier.location, "Undefined modifier " + missingModifier.identifier));
			
			return messages;
		}
	}

	// Use Falsifiable<ModuleExpansion>; remove message from ModuleExpansion
	public ModuleExpansion expandModule(Module module /*, expansion arguments (asts) here?*/) throws IOException {
		ModuleExpansion moduleExpansion = new ModuleExpansion();
		
		moduleExpansion.module = module;
		
		for(Module dependerModule: module.parseContext.getDependers()) {
			for(Parser.IdentifierUsage usage: module.parseContext.getResolvedDependencies(dependerModule)) {
				ModuleExpansion dependerExpansion = expandModule(dependerModule);
				usage.astPlaceHolder.put(Symbol.Map.KEY_TYPE, Symbol.Map.AST_TYPE_MODULE_USAGE);
				usage.astPlaceHolder.put(Symbol.Map.KEY_VALUE, dependerExpansion.ast);
			}
		}
		
		// Make the following projection of module:
		// - Apply expansion arguments
		
		Symbol expansion;
		
		if(module.modifiers.length > 0) {
			// - Apply modifiers
			
			//   - Build a pipe line based on the modifiers
			HLPipeline pipeline = new HLPipeline();
			
			// Consider it is more appropriate to reverse the modifiers' application sequence?
			for(Module.Modifier modifier: module.modifiers) {
				Module modifierModule = getModule(modifier.identifier);
				if(modifierModule != null) {
					Symbol modifierModuleExpansion = expandModule(modifierModule).ast;
					HLCodeBuilder modifierModuleExpansionBuilder = HLCodeBuilder.Factory.fromAST(modifierModuleExpansion);
					pipeline.append(modifierModuleExpansionBuilder);
				} else
					moduleExpansion.missingModifiers.add(modifier);
			}
			
			HLCodeBuilder modification = new HLEntryPointBuilder(pipeline);
			CodeBuilder codeBuilder = new CodeBuilder();
			modification.appendTo(codeBuilder);
			CodeBuilder.Build modificationBuild = codeBuilder.build();
			
			//   - Then evaluate that pipeline based on an input with a single element: the module's body ast
			IOBuffer input = new IOBuffer(Arrays.asList(module.bodyAst));
			IOBuffer output = new IOBuffer();
			Machine machine = new Machine(input, output, modificationBuild.code, new Symbol[modificationBuild.variableCount], modificationBuild.interCount);
			
			while(machine.getResult() == Machine.RESULT_ABSENT)
				machine.evaluate();

			//   - The output (a single element is expected) is then considered the expanded module's body ast
			//   - If the output is not as expected, then an error, about this, is promoted
			output.pipe();
			
			expansion = output.peek();
			
			//   - Circular dependencies among modifiers and modifyees are (for now) not allowed and is promoted as an error
			//     since infinite loops may occur during compilation 
			//       - if infinite loops can statically shown as impossible, then it is ok?
			//       - what about very expensive modifiers?
			//	   - E.g., the following script yields an error:
			//       def mod1 mod2(...)
			//       def mod2 mod1(...)

			// - Resolve derived identifier usages
		} else {
//			expansion = Symbol.astCall(module.bodyAst);
			expansion = module.bodyAst;
		}
		
		moduleExpansion.ast = expansion;
		
		return moduleExpansion;
	}
	
	// Use Falsifiable<CodeBuilder.Build>
	public CodeBuilder.Build build(String entryPointIdentifier) throws IOException {
		// - Resolve generic identifier usages
		for(Module moduleNeedsResolution: modulesNeedResolution) {
			for(String unresolvedIdentifierUsage: moduleNeedsResolution.parseContext.unresolvedDependency()) {
				Module dependerModule = getModule(unresolvedIdentifierUsage);
				if(dependerModule != null)
					moduleNeedsResolution.parseContext.resolveDependency(unresolvedIdentifierUsage, dependerModule);
			}
		}
		modulesNeedResolution.clear();
		
		Module entryPointModule = getModule(entryPointIdentifier);
		ModuleExpansion entryPointExpansion = expandModule(entryPointModule);
		
		if(getParseMessages().size() == 0) {
			HLCodeBuilder hlCodeBuilder = new HLEntryPointBuilder(HLCodeBuilder.Factory.fromAST(entryPointExpansion.ast)); 
			CodeBuilder codeBuilder = new CodeBuilder();
			
			hlCodeBuilder.appendTo(codeBuilder);
			
			return codeBuilder.build();
		}
		
		return null;
	}

//	private boolean isDefined(String identifier) {
//		return getModule(identifier) != null;
//	}

//	public void resolve() {
//		for(Module module: modules) {
//			module.parseContext.
//		}
//	}
}
