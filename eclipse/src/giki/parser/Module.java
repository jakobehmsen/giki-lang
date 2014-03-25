package giki.parser;

import java.util.ArrayList;

import giki.runtime.Container;
import giki.runtime.Symbol;

public class Module {
	public static class Modifier {
		public final ResourceLocation location;
		public final String identifier;
		
		public Modifier(ResourceLocation location, String identifier) {
			this.location = location;
			this.identifier = identifier;
		}
	}
	
	public final String ressourcePath;
	public final String path;
	public final String name;
//	public final Symbol bodyAst;
	public final Func0<Symbol> bodyInterpolater;
	public final Parser.ParseContext parseContext;
//	public final CodeBuilder codeBuilder;
//	private CodeBuilder.Build build;
	public final Modifier[] modifiers;
	public final Module[] innerModules;
//	public final ArrayList<HLIdentifier> dependencies;
//	public final ArrayList<ParseMessage> parseMessages = new ArrayList<ParseMessage>();
	
	private Symbol reified;
	
//	public Module(String ressourcePath, String path, String name, Symbol bodyAst, CodeBuilder codeBuilder, String[] modifiers, Module[] innerModules, ArrayList<HLIdentifier> dependencies) {
	public Module(String ressourcePath, String path, String name, Func0<Symbol> bodyInterpolater, Parser.ParseContext parseContext, Modifier[] modifiers, Module[] innerModules) {
		this.ressourcePath = ressourcePath;
		this.path = path;
		this.name = name;
		this.bodyInterpolater = bodyInterpolater;
		this.parseContext = parseContext;
//		this.codeBuilder = codeBuilder;
		this.modifiers = modifiers;
		this.innerModules = innerModules;
//		this.dependencies = dependencies;
	}

	public Symbol reify() {
		if(reified != null)
			return reified;
		
		ArrayList<Symbol> reifiedModifiersBuilder = new ArrayList<Symbol>();
		for(Modifier modifier: modifiers)
			reifiedModifiersBuilder.add(Symbol.getIdentifier(modifier.identifier));
		
		ArrayList<Symbol> reifiedInnerModulesBuilder = new ArrayList<Symbol>();
		for(Module innerModule: innerModules)
			reifiedInnerModulesBuilder.add(innerModule.reify());
		
		reified = new Symbol.Map()
			.with(Symbol.Map.KEY_TYPE, Symbol.Map.AST_TYPE_MODULE)
			.with(Symbol.Map.KEY_MODIFIERS, new Container.Default(reifiedModifiersBuilder))
			.with(Symbol.Map.KEY_INNER_MODULES, new Container.Default(reifiedInnerModulesBuilder))
			.with(Symbol.Map.KEY_BODY, bodyInterpolater.call());
		
		return reified;
	}
	
//	public CodeBuilder.Build getBuild() {
//		if(build == null)
//			build = codeBuilder.build();
//		return build;
//	}
}
