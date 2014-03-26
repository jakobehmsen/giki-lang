package giki.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import giki.runtime.CodeBuilder;
import giki.runtime.CodeBuilder.Build;
import giki.runtime.Symbol;

public class ModuleApplication {
	private ResourceStore resourceStore;
	private String resourceEntryPointName;
	private Hashtable<String, Module> pathToResolvedModule = new Hashtable<String, Module>();
	private Hashtable<String, Module> pathToModule = new Hashtable<String, Module>();
	private Hashtable<String, BuildDependencies> identifierToCachedBuild = new Hashtable<String, BuildDependencies>();
	private ArrayList<ParseMessage> parseMessages = new ArrayList<ParseMessage>();
	
	public ModuleApplication(ResourceStore resourceStore, String resourceEntryPointName) {
		this.resourceStore = resourceStore;
		this.resourceEntryPointName = resourceEntryPointName;
	}
	
	public Module getModule(String identifier) throws IOException {
		Module module = pathToResolvedModule.get(identifier);
		
		if(module == null) {
			module = pathToModule.get(identifier);
			
			if(module == null) {
				Parser parser = new Parser(System.out);
				boolean isEntryPoint = identifier.equals(resourceEntryPointName);
				String resourceName = identifier.split("/")[0];
				if(resourceStore.exists(resourceName)) {
					Resource resource = resourceStore.getResource(resourceName);
					Module rootModule = parser.toModule(resource);
					loadModule(rootModule);
					module = pathToModule.get(identifier);
				} else {
					return null;
				}
			}
			

			pathToResolvedModule.put(identifier, module);
			resolveModuleDependencies(module/*, identifierToModule*/);
			loadParseMessages(module);
		}
		
		return module;
	}
	
	public ArrayList<ParseMessage> getParseMessages() {
		return parseMessages;
	}
	
	private void loadModule(Module module) throws IOException {
		indexModulePaths(module);
		Hashtable<String, Module> identifierToModule = new Hashtable<String, Module>();
		indexModuleIdentifiers(module, identifierToModule);
//		resolveModuleDependencies(module, identifierToModule);
//		
//		loadParseMessages(module);
	}
	
	private void loadParseMessages(Module module) {
		parseMessages.addAll(module.parseContext.getMessages());
		
		for(Module innerModule: module.innerModules)
			loadParseMessages(innerModule);
	}
	
	private void indexModulePaths(Module module) {
		pathToModule.put(module.path + module.name, module);
		
		for(Module innerModule: module.innerModules)
			indexModulePaths(innerModule);
	}
	
	private void indexModuleIdentifiers(Module module, Hashtable<String, Module> identifierToModule) {
		if(!pathToModule.containsKey(module.name))
			pathToModule.put(module.name, module);
		
		for(Module innerModule: module.innerModules)
			indexModuleIdentifiers(innerModule, identifierToModule);
	}
	
	private void resolveModuleDependencies(Module module/*, Hashtable<String, Module> identifierToModule*/) throws IOException {
		for(String identifier: module.parseContext.unresolvedDependency()) {
			Module depender = getModule(identifier);
			if(depender != null) {
				Symbol dependerAst = null;//depender.bodyAst;
				
				// Apply modifiers
				dependerAst = Symbol.astCall(dependerAst);
				// The modified AST of a module can be cached and reused?
				
				// Resolved the type
//				module.parseContext.resolveIdentifierUsageAsModuleUsage(identifier, dependerAst);
			}
//			identifierUsage.astPlaceHolder.put(Symbol.Map.KEY_TYPE, Symbol.Map.AST_TYPE_MODULE_USAGE);
		}
		
//		for(HLIdentifier moduleDependency: module.dependencies) {
//			Module depender = getModule(moduleDependency.getIdentifier());
//			if(depender != null) {
//				moduleDependency.setDepender(depender);
//			}/* else {
//				parseMessages.add(new ParseMessage(moduleDependency.getFileLocation(), "Cannot resolve " + moduleDependency.getIdentifier() + "."));
//			}*/
//		}
	}
	
	public CodeBuilder.Build getEntryPointModuleBuild() throws IOException {
		CodeBuilder.Build entryPointModuleBuild = getModuleBuild(true, null, resourceEntryPointName);
		
//		for(Map.Entry<String, BuildDependencies> entry: identifierToCachedBuild.entrySet()) {
//			if(entry.getValue().build.code == null) {
//				for(ResourceLocation dependency: entry.getValue().dependencies)
//					parseMessages.add(new ParseMessage(dependency, "Cannot resolve " + entry.getKey() + "."));
//			}
//		}
		
		return entryPointModuleBuild;
	}
	
	private static class BuildDependencies {
		private CodeBuilder.Build build = null;//new Build();
		private ArrayList<ResourceLocation> dependencies = new ArrayList<ResourceLocation>();
	}
	
	public CodeBuilder.Build getModuleBuild(boolean isEntryPoint, ResourceLocation resourceLocation, String identifier) throws IOException {
		BuildDependencies cachedBuild = identifierToCachedBuild.get(identifier);
		if(cachedBuild == null) {
			cachedBuild = new BuildDependencies();
			identifierToCachedBuild.put(identifier, cachedBuild);
			Module module = getModule(identifier);
			if(module != null) {
				if(module.parseContext.getMessages().isEmpty()) {
					HLCodeBuilder hlCodeBuilder = HLCodeBuilder.Factory.fromAST(null/*module.bodyAst*/);
					CodeBuilder codeBuilder = new CodeBuilder();
					
					Object falseJumpLabel = codeBuilder.createLabel();
					codeBuilder.setFalseJump(falseJumpLabel);
					
					hlCodeBuilder.appendTo(codeBuilder);
					
					codeBuilder
						.acceptFrame()
						.label(falseJumpLabel)
						.rejectFrame();
					
//					codeBuilder.build(cachedBuild.build, this);
	//				module.codeBuilder.build(cachedBuild.build, this);
				}
			}
		}
		cachedBuild.dependencies.add(resourceLocation);
		return cachedBuild.build;
	}
	
	public Symbol getModuleAst(ResourceLocation resourceLocation, String identifier) throws IOException {
		// Find the particular module corresponding to the supplied identifier
		// Return the module reified as an ast

		Module module = getModule(identifier);
		
		return module.reify(null);
	}
}
