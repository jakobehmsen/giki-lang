package giki.parser;

import giki.antlr4.GikiLexer;
import giki.antlr4.GikiParser;
import giki.antlr4.GikiParser.AssignmentContext;
import giki.antlr4.GikiParser.ComposeContext;
import giki.antlr4.GikiParser.DecisionContext;
import giki.antlr4.GikiParser.ExpressionContext;
import giki.antlr4.GikiParser.ExpressionsContext;
import giki.antlr4.GikiParser.GroupContext;
import giki.antlr4.GikiParser.IdentifierContext;
import giki.antlr4.GikiParser.IntervalContext;
import giki.antlr4.GikiParser.ListContext;
import giki.antlr4.GikiParser.MapContext;
import giki.antlr4.GikiParser.ModuleContext;
import giki.antlr4.GikiParser.ModuleModifiersContext;
import giki.antlr4.GikiParser.NamedPrimitiveContext;
import giki.antlr4.GikiParser.PipelineContext;
import giki.antlr4.GikiParser.PipelineInterContext;
import giki.antlr4.GikiParser.PreFixFileContext;
import giki.antlr4.GikiParser.PreFixRestContext;
import giki.antlr4.GikiParser.IoOperationContext;
import giki.antlr4.GikiParser.ProduceVerbatimContext;
import giki.antlr4.GikiParser.ProgramContext;
import giki.antlr4.GikiParser.ProgramElementsContext;
import giki.antlr4.GikiParser.QuoteContext;
import giki.antlr4.GikiParser.SlotSetContext;
import giki.antlr4.GikiParser.TermContext;
import giki.antlr4.GikiParser.TermsContext;
import giki.antlr4.GikiParser.VariableDeclarationContext;
import giki.diagnostics.StopWatch;
import giki.parser.HLCodeBuilder.Validation;
import giki.runtime.CodeBuilder;
import giki.runtime.Container;
import giki.runtime.Instruction;
import giki.runtime.Symbol;
import giki.runtime.SymbolTable;
import giki.runtime.Symbol.Map;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Parser {
	private PrintStream out;
	
	public Parser(PrintStream out) {
		this.out = out;
	}
	
	public Module toModule(final Resource resource) throws IOException {
		final InputStream inputStream = resource.getInputStream();
		String getRessourcePath = resource.getPath();
		final BufferedInputStream bufferedInput = new BufferedInputStream(inputStream);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ANTLRInputStream antlrInputStream = new ANTLRInputStream(bufferedInput);
		GikiLexer lexer = new giki.antlr4.GikiLexer(antlrInputStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		giki.antlr4.GikiParser parser = new GikiParser(tokenStream);
		parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
		
		final ArrayList<ParseMessage> parseMessages = new ArrayList<ParseMessage>();
		
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(@NotNull Recognizer<?, ?> recognizer,
					@Nullable Object offendingSymbol, int line,
					int charPositionInLine, @NotNull String msg,
					@Nullable RecognitionException e) {
				parseMessages.add(new ParseMessage(new ResourceLocation(resource.getName(), line, charPositionInLine), msg));
			}
		});
		
		ProgramContext programCtx = null;
		
		try {
			programCtx = parser.program();  // STAGE 1
		}
		catch (Exception ex) {
			lexer.reset(); // rewind input stream
		    parser.reset();
		    parser.getInterpreter().setPredictionMode(PredictionMode.LL);
		    programCtx = parser.program();  // STAGE 2
		    // if we parse ok, it's LL not SLL
		}
		stopWatch.stop();
		stopWatch.reset();

		stopWatch.start();
		Module module = toModule(new Module.Modifier[0], getRessourcePath, "", resource.getName(), programCtx.programElements());
		stopWatch.stop();
		
//		module.parseMessages.addAll(parseMessages);
		
		return module;
	}
	
	private static class MetaModule {
//		public final Symbol ast;
//		public final HLCodeBuilder.Validation validation;
//		public final CodeBuilder codeBuilder;
//		
//		public MetaModule(Symbol ast, Validation validation, CodeBuilder codeBuilder) {
//			this.ast = ast;
//			this.validation = validation;
//			this.codeBuilder = codeBuilder;
//		}
		
		public final Symbol ast;
		public final ParseContext parseContext;
		
		public MetaModule(Symbol ast, ParseContext parseContext) {
			this.ast = ast;
			this.parseContext = parseContext;
		}
	}
	
	private static Module toModule(String ressourcePath, String path, String name, ModuleContext moduleCtx) {
		ArrayList<Module.Modifier> modifiersBuilder = new ArrayList<Module.Modifier>();
		
		for(TerminalNode modifier: moduleCtx.moduleModifiers().ID())
			modifiersBuilder.add(new Module.Modifier(new ResourceLocation(ressourcePath, modifier.getSymbol()), modifier.getText()));
		
		Module.Modifier[] modifiers = new Module.Modifier[modifiersBuilder.size()];
		modifiersBuilder.toArray(modifiers);
		
		return toModule(modifiers, ressourcePath, path, name, moduleCtx.programElements());
	}
	
	private static Module toModule(Module.Modifier[] modifiers, String ressourcePath, String path, String name, ProgramElementsContext programElementsCtxs) {
		ArrayList<Module> innerModulesBuilder = new ArrayList<Module>();
		ArrayList<ParserRuleContext> expressionsCtxs = new ArrayList<ParserRuleContext>();
		
		for(ModuleContext innerModuleCtx: programElementsCtxs.module()) {
			Module innerModule = toModule(ressourcePath, path + name + "/", innerModuleCtx.selector.getText(), innerModuleCtx);
			innerModulesBuilder.add(innerModule);
		}
		
		for(ExpressionsContext expressionsCtx: programElementsCtxs.expressions()) {
			expressionsCtxs.add(expressionsCtx);
		}
		
		Module[] innerModules = new Module[innerModulesBuilder.size()];
		innerModulesBuilder.toArray(innerModules);
		
		MetaModule metaModule = toBuild(ressourcePath, expressionsCtxs);
		
		return new Module(ressourcePath, path, name, metaModule.ast, metaModule.parseContext, /*metaModule.codeBuilder,*/ modifiers, innerModules/*, metaModule.validation.getDependencies()*/);
	}
	
	public static class IdentifierUsage {
		// Argument asts also?
		public final ResourceLocation location;
		public final Symbol.Map astPlaceHolder;
		public final String identifier;
		
		public IdentifierUsage(ResourceLocation location, Symbol.Map astPlaceHolder, String identifier) {
			this.location = location;
			this.astPlaceHolder = astPlaceHolder;
			this.identifier = identifier;
		}
	}
	
	public static class ParseContext {
		private ArrayList<ParseMessage> messages = new ArrayList<ParseMessage>();
		private Hashtable<String, ArrayList<IdentifierUsage>> unresolvedIdentifierUsages = new Hashtable<String, ArrayList<IdentifierUsage>>();
		private Hashtable<Module, ArrayList<IdentifierUsage>> resolvedIdentifierUsages = new Hashtable<Module, ArrayList<IdentifierUsage>>();
		private HashSet<String> definedIdentifiers = new HashSet<String>();
		private HashSet<String> variables = new HashSet<String>();
		
		public void declareVariable(ResourceLocation location, String identifier) {
			if(variables.contains(identifier))
				messages.add(new ParseMessage(location, "Duplicate variable declaration of " + identifier + "."));
			
			variables.add(identifier);
		}

		public void assignVariable(ResourceLocation location, String identifier, Symbol value) {
			if(!variables.contains(identifier))
				messages.add(new ParseMessage(location, "Assigning value to undeclared variable " + identifier + "."));
		}

		public Symbol.Map identifierUsage(ResourceLocation location, String identifier) {
			Symbol.Map astPlaceHolder = (Symbol.Map)Symbol.astPlaceHolder(identifier);
			
			if(variables.contains(identifier)) {
				astPlaceHolder.put(Symbol.Map.KEY_TYPE, Symbol.Map.AST_TYPE_VARIABLE_USAGE);
			} else {
				ArrayList<IdentifierUsage> usages = unresolvedIdentifierUsages.get(identifier);
				if(usages == null) {
					usages = new ArrayList<Parser.IdentifierUsage>();
					unresolvedIdentifierUsages.put(identifier, usages);
				}
				usages.add(new IdentifierUsage(location, astPlaceHolder, identifier));
			}
			
			return astPlaceHolder;
		}

		public Set<String> unresolvedDependency() {
			return unresolvedIdentifierUsages.keySet();
		}

		public Set<Module> getDependers() {
			return resolvedIdentifierUsages.keySet();
		}

		public void resolveDependency(String identifier, Module depender) {
			ArrayList<IdentifierUsage> usages = unresolvedIdentifierUsages.get(identifier);
			
			if(usages != null) {
//				for(IdentifierUsage usage: usages) {
//					usage.astPlaceHolder.put(Symbol.Map.KEY_TYPE, Symbol.Map.AST_TYPE_MODULE_USAGE);
//					usage.astPlaceHolder.put(Symbol.Map.KEY_VALUE, ast);
//				}
				ArrayList<IdentifierUsage> resolvedUsages = unresolvedIdentifierUsages.get(depender);
				if(resolvedUsages != null)
					resolvedUsages.addAll(usages);
				else
					resolvedIdentifierUsages.put(depender, usages);
				unresolvedIdentifierUsages.remove(identifier);
			}
		}

		public ArrayList<IdentifierUsage> getResolvedDependencies(Module module) {
			return resolvedIdentifierUsages.get(module);
		}
		
		public ArrayList<ParseMessage> getMessages() {
			ArrayList<ParseMessage> messages = new ArrayList<ParseMessage>(this.messages);
			
			for(Entry<String, ArrayList<IdentifierUsage>> unresolvedIdentifierUsage: unresolvedIdentifierUsages.entrySet()) {
				String identifier = unresolvedIdentifierUsage.getKey();
				if(!definedIdentifiers.contains(identifier)) {
					ArrayList<IdentifierUsage> usages = unresolvedIdentifierUsage.getValue();
					for(IdentifierUsage usage: usages)
						messages.add(new ParseMessage(usage.location, "Unresolved identifier " + usage.identifier));
				}
			}
			
			return messages;
		}

		public void markIdentifierAsDefined(String identifier) {
			definedIdentifiers.add(identifier);
		}
	}
	
	private static MetaModule toBuild(String resourceName, ArrayList<ParserRuleContext> ctxs) {
		ParseContext parseContext = new ParseContext();
		Symbol ast = toSymbol(parseContext, resourceName, ctxs);
		return new MetaModule(ast, parseContext);
	}
	
	public Symbol toSymbol(final Resource resource) throws IOException {
		final InputStream inputStream = resource.getInputStream();
		final BufferedInputStream bufferedInput = new BufferedInputStream(inputStream);

		ANTLRInputStream antlrInputStream = new ANTLRInputStream(bufferedInput);
		GikiLexer lexer = new giki.antlr4.GikiLexer(antlrInputStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		giki.antlr4.GikiParser parser = new GikiParser(tokenStream);
		parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
		
		final ArrayList<ParseMessage> parseMessages = new ArrayList<ParseMessage>();
		
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(@NotNull Recognizer<?, ?> recognizer,
					@Nullable Object offendingSymbol, int line,
					int charPositionInLine, @NotNull String msg,
					@Nullable RecognitionException e) {
				parseMessages.add(new ParseMessage(new ResourceLocation(resource.getName(), line, charPositionInLine), msg));
			}
		});
		
		ProgramContext programCtx = null;
		
		try {
			programCtx = parser.program();  // STAGE 1
		}
		catch (Exception ex) {
			lexer.reset(); // rewind input stream
		    parser.reset();
		    parser.getInterpreter().setPredictionMode(PredictionMode.LL);
		    programCtx = parser.program();  // STAGE 2
		    // if we parse ok, it's LL not SLL
		}

		Symbol module = toSymbol(resource.getPath(), resource.getPath(), resource.getName(), programCtx.programElements());
		
		return module;
	}
	
	private static Symbol toSymbol(String ressourcePath, String path, String name, ProgramElementsContext programElementsCtxs) {
		ArrayList<Symbol> modifiersBuilder = new ArrayList<Symbol>();
		ArrayList<Symbol> innerModulesBuilder = new ArrayList<Symbol>();
		ArrayList<ParserRuleContext> expressionsCtxs = new ArrayList<ParserRuleContext>();
		
		for(ModuleContext moduleCtx: programElementsCtxs.module()) {
			for(TerminalNode modifier: moduleCtx.moduleModifiers().ID())
				modifiersBuilder.add(Symbol.getIdentifier(modifier.getText()));
			Symbol innerModule = toSymbol(ressourcePath, path + name + "/", moduleCtx.selector.getText(), moduleCtx.programElements());
			innerModulesBuilder.add(innerModule);
		}
		
		for(ExpressionsContext expressionsCtx: programElementsCtxs.expressions()) {
			expressionsCtxs.add(expressionsCtx);
		}
		
		ParseContext parseContext = new ParseContext();
		Symbol body = toSymbol(parseContext, ressourcePath, expressionsCtxs);
		
		return new Symbol.Map()
			.with(Symbol.Map.KEY_TYPE, Symbol.getIdentifier("module"))
			.with(Symbol.Map.KEY_MODIFIERS, new Container.Default(modifiersBuilder))
			.with(Symbol.Map.KEY_INNER_MODULES, new Container.Default(innerModulesBuilder))
			.with(Symbol.Map.KEY_BODY, body);
	}
	
	public static Symbol toSymbol(ParseContext parseContext, String resourceName, ParserRuleContext ctx) {
		switch(ctx.getRuleIndex()) {
		case giki.antlr4.GikiParser.RULE_expressions: {
			ExpressionsContext expressionsCxt = (ExpressionsContext)ctx;
			return toSymbol(parseContext, resourceName, expressionsCxt.expression());
		}
		case giki.antlr4.GikiParser.RULE_expression: {
			ExpressionContext expressionContext = (ExpressionContext)ctx;
			return toSymbol(parseContext, resourceName, (ParserRuleContext)expressionContext.getChild(0));
		}
		case giki.antlr4.GikiParser.RULE_variableDeclaration: {
			VariableDeclarationContext variableDeclarationCxt = (VariableDeclarationContext)ctx;
			
			if(variableDeclarationCxt.isDeclaration == null) {
				return toSymbol(parseContext, resourceName, (ParserRuleContext)variableDeclarationCxt.getChild(0));
			} else {
				String identifier = variableDeclarationCxt.ID().getText();
				Symbol initialization;
				
				parseContext.declareVariable(
					new ResourceLocation(resourceName, variableDeclarationCxt.ID().getSymbol()), 
					identifier
				);
				
				if(variableDeclarationCxt.hasInitialization != null) {
					initialization = toSymbol(parseContext, resourceName, variableDeclarationCxt.value);
					parseContext.assignVariable(new ResourceLocation(resourceName, variableDeclarationCxt), identifier, initialization);
				}
				else
					initialization = null;

				return Symbol.astVariableDeclaration(identifier, initialization);
			}
		}
		case giki.antlr4.GikiParser.RULE_assignment: {
			AssignmentContext assignmentCtx = 
				(AssignmentContext)ctx;
			
			if(assignmentCtx.isAssignment == null) {
				return toSymbol(parseContext, resourceName, (ParserRuleContext)assignmentCtx.getChild(0));
			} else {
				String identifier = assignmentCtx.id.getText();
				
				Symbol value = toSymbol(parseContext, resourceName, assignmentCtx.value);
				
				parseContext.assignVariable(new ResourceLocation(resourceName, assignmentCtx), identifier, value);
				
				return Symbol.astAssignment(identifier, value);
			}
		}
		case giki.antlr4.GikiParser.RULE_decision: {
			DecisionContext decisionContext = (DecisionContext)ctx;
			
			if(decisionContext.decisionTail().pipeline() == null || decisionContext.decisionTail().pipeline().isEmpty())
				return toSymbol(parseContext, resourceName, (ParserRuleContext)decisionContext.getChild(0));
			else {
				ArrayList<Symbol> alternatives = new ArrayList<Symbol>();
				Symbol firstAlternative = toSymbol(parseContext, resourceName, decisionContext.value);
				
				alternatives.add(firstAlternative);
				
				for(PipelineContext alternativeCtx: decisionContext.decisionTail().pipeline()) {
					Symbol alternative = toSymbol(parseContext, resourceName, alternativeCtx);
					alternatives.add(alternative);
				}
				
				return Symbol.astDecision(alternatives);
			}
		}
		case giki.antlr4.GikiParser.RULE_pipeline: {
			PipelineContext pipelineContext = (PipelineContext)ctx;
			
			if(pipelineContext.hasFrom == null && pipelineContext.hasInters == null && pipelineContext.hasTo == null)
				return toSymbol(parseContext, resourceName, pipelineContext.lhs);
			else {
				Symbol codeBuilder;
				
				ArrayList<Symbol> pipes = new ArrayList<Symbol>();

				Symbol from = null;
				Symbol to = null;
				Symbol firstPipe = toSymbol(parseContext, resourceName, pipelineContext.lhs);
				
				if(pipelineContext.hasFrom != null) {
					from = firstPipe;
					firstPipe = toSymbol(parseContext, resourceName, pipelineContext.hasFrom.body);
				}
				
				if(pipelineContext.hasTo != null) {
					to = toSymbol(parseContext, resourceName, pipelineContext.hasTo.body);
				}
				
				pipes.add(firstPipe);
				
				for(PipelineInterContext interCtx: pipelineContext.pipelineInter()) {
					Symbol pipe = toSymbol(parseContext, resourceName, interCtx.preFixOperation());
					pipes.add(pipe);
				}
				
				codeBuilder = Symbol.astPipeline(pipes);
				
				if(to != null)
					codeBuilder = Symbol.astTo(to, codeBuilder);
				
				if(from != null)
					codeBuilder = Symbol.astFrom(from, codeBuilder);
				
				return Symbol.astIOScope(codeBuilder);
			}
		}
		case giki.antlr4.GikiParser.RULE_preFixOperation: {
			return toSymbol(parseContext, resourceName, (ParserRuleContext)ctx.getChild(0));
		}
		case giki.antlr4.GikiParser.RULE_preFixRest: {
			PreFixRestContext preFixRestContext = (PreFixRestContext)ctx;
			Symbol toRepeatNode = toSymbol(parseContext, resourceName, preFixRestContext.expr);
			
			return Symbol.astLoop(Symbol.astSequence(
				Symbol.astNot(Symbol.astInstruction(Instruction.TYPE_EOF)),
				toRepeatNode
			));
		}
		case giki.antlr4.GikiParser.RULE_preFixFile: {
			PreFixFileContext preFixFileCtx = (PreFixFileContext)ctx;
			
			Symbol expr = toSymbol(parseContext, resourceName, preFixFileCtx.expr);
			
			return Symbol.astPipeline(expr, Symbol.astInstruction(Instruction.TYPE_FILE));
		}
		case giki.antlr4.GikiParser.RULE_compose: {
			ComposeContext composeCtx = (ComposeContext)ctx;
			
			if(composeCtx.isCompose == null)
				return toSymbol(parseContext, resourceName, (ParserRuleContext)composeCtx.lhs);
			else {
				ArrayList<Symbol> items = new ArrayList<Symbol>();
				
				Symbol firstMap = toSymbol(parseContext, resourceName, (ParserRuleContext)composeCtx.lhs);
				items.add(firstMap);
				
				for(TermsContext termsCtx: composeCtx.terms().subList(1, composeCtx.terms().size())) {
					Symbol map = toSymbol(parseContext, resourceName, termsCtx);
					items.add(map);
				}
				
				return Symbol.astReduce(items, Symbol.astInstruction(Instruction.TYPE_MAP_UNION));
			}
		}
		case giki.antlr4.GikiParser.RULE_interval: {
			IntervalContext intervalCtx = (IntervalContext)ctx;
			
			Symbol lhsIdentifier = Symbol.getIdentifier(intervalCtx.lhs.PRODUCTION_STRING().getText());
			Symbol rhsIdentifier = Symbol.getIdentifier(intervalCtx.rhs.PRODUCTION_STRING().getText());
			
			return Symbol.astInterval(lhsIdentifier, rhsIdentifier);
		}
		case giki.antlr4.GikiParser.RULE_terms: {
			TermsContext termsCtx = (TermsContext)ctx;
			return toSymbol(parseContext, resourceName, termsCtx.term());
		}
		case giki.antlr4.GikiParser.RULE_term: {
			TermContext termCtx = (TermContext)ctx;
			
			Symbol body = toSymbol(parseContext, resourceName, (ParserRuleContext)termCtx.termValue().getChild(0));
	
			if(termCtx.lookupChain() != null) {
				ArrayList<Symbol> slotIdentifiers = new ArrayList<Symbol>();
				
				for(TerminalNode identifierNode: termCtx.lookupChain().ID()) {
					String slotIdentifier = identifierNode.getText();
					slotIdentifiers.add(Symbol.getIdentifier(slotIdentifier));
				}
				
				body = Symbol.astLookupChain(body, slotIdentifiers);
			}
			
			if(termCtx.isNot != null)
				body = Symbol.astNot(body);
			
			if(termCtx.isMulti == null)
				return body;
			else
				return Symbol.astLoop(body);
		}
		case giki.antlr4.GikiParser.RULE_ioOperation: {
			IoOperationContext ioOperationCtx = (IoOperationContext)ctx;
			
			Symbol expr = toSymbol(parseContext, resourceName, (ParserRuleContext)ioOperationCtx.getChild(0));
			
			if(ioOperationCtx.IO_SCOPE() != null) {
				int ioScope = Integer.parseInt(ioOperationCtx.IO_SCOPE().getText());
				return Symbol.astIOScopeUsage(ioScope, expr);
			} else {
				return expr;
			}
		}
		case giki.antlr4.GikiParser.RULE_consumeString: {
			String identifierText = replaceEscChars(ctx.getText());
			Symbol identifier = Symbol.getIdentifier(identifierText);
			return Symbol.astInstruction(Instruction.TYPE_PEEK_EQUALS_CONSUME, identifier);
		}
		case giki.antlr4.GikiParser.RULE_produceString: {
			String identifierText = replaceEscChars(ctx.getText());
			Symbol identifier = Symbol.getIdentifier(identifierText);
			return Symbol.astInstruction(Instruction.TYPE_QUOTE, identifier);
		}
		case giki.antlr4.GikiParser.RULE_consumeInteger: {
			String integerText = ctx.getText();
			int integerValue = Integer.parseInt(integerText);
			Symbol integer = Symbol.getInteger(integerValue);
			return Symbol.astInstruction(Instruction.TYPE_PEEK_EQUALS_CONSUME, integer);
		}
		case giki.antlr4.GikiParser.RULE_produceInteger: {
			String integerText = ctx.getText();
			int integerValue = Integer.parseInt(integerText);
			Symbol integer = Symbol.getInteger(integerValue);
			return Symbol.astInstruction(Instruction.TYPE_QUOTE, integer);
		}
		case giki.antlr4.GikiParser.RULE_namedPrimitive: {
			NamedPrimitiveContext namedPrimitiveCtx = (NamedPrimitiveContext)ctx;
			TerminalNode namedPrimitiveToken = (TerminalNode)namedPrimitiveCtx.getChild(0);
			
			switch(namedPrimitiveToken.getSymbol().getType()) {
			case giki.antlr4.GikiLexer.NAMED_PRIMITIVE_EOF:
				return Symbol.astInstruction(Instruction.TYPE_EOF);
			}
			
			break;
		}
		case giki.antlr4.GikiParser.RULE_move: {
			return Symbol.astInstruction(Instruction.TYPE_MOVE);
		}
		case giki.antlr4.GikiParser.RULE_peek: {
			return Symbol.astInstruction(Instruction.TYPE_PEEK);
		}
		case giki.antlr4.GikiParser.RULE_next: {
			return Symbol.astInstruction(Instruction.TYPE_CONSUME);
		}
		case giki.antlr4.GikiParser.RULE_identifier: {
			IdentifierContext identifierCtx = (IdentifierContext)ctx;
			
			String identifier = identifierCtx.ID().get(0).getText();
			for(int i = 1; i < identifierCtx.ID().size(); i++)
				identifier += "/" + identifierCtx.ID().get(i).getText();
			
			Symbol.Map astPlaceHolder = parseContext.identifierUsage(new ResourceLocation(resourceName, identifierCtx), identifier);
			
			return astPlaceHolder;
		}
		case giki.antlr4.GikiParser.RULE_group: {
			GroupContext groupContext = (GroupContext)ctx;
			return toSymbol(parseContext, resourceName, groupContext.expressions().expression());
		}
		case giki.antlr4.GikiParser.RULE_list: {
			ListContext semanticTermTransitionListContext = (ListContext)ctx;
	
			if(semanticTermTransitionListContext.expressions() != null) {
				Symbol initialization = toSymbol(parseContext, resourceName, semanticTermTransitionListContext.expressions().expression());
				
				return Symbol.astList(initialization);
			} else {
				return Symbol.astList();
			}
		}
		case giki.antlr4.GikiParser.RULE_map: {
			MapContext mapCtx = (MapContext)ctx;
			
			ArrayList<Symbol> slotSets = new ArrayList<Symbol>();

			int[] slots = new int[mapCtx.slotSet().size()];
			
			for(int i = 0; i < slots.length; i++) {
				SlotSetContext slotSetCtx = mapCtx.slotSet().get(i);
				String slotIdentifier = slotSetCtx.ID().getText();
				Symbol slotValue = toSymbol(parseContext, resourceName, slotSetCtx.value);
				
				Symbol slotSet = new Symbol.Map()
					.with(Symbol.Map.KEY_SLOT, Symbol.getIdentifier(slotIdentifier))
					.with(Symbol.Map.KEY_VALUE, slotValue);
				slotSets.add(slotSet);
			}
			
			return Symbol.astMap(slotSets);
		}
		case giki.antlr4.GikiParser.RULE_produceVerbatim: {
			ProduceVerbatimContext produceVerbatimCtx = (ProduceVerbatimContext)ctx;
			String text = produceVerbatimCtx.VERBATIM().getText().replaceAll("\\\\´", "\\´");
	
			ArrayList<Symbol> quotes = new ArrayList<Symbol>();
			
			for(int i = 0; i < text.length(); i++)
				quotes.add(Symbol.astQuote("" + text.charAt(i)));
			
			return Symbol.astGroup(quotes);
		}
		case giki.antlr4.GikiParser.RULE_quote: {
			QuoteContext quoteCtx = (QuoteContext)ctx;
			
			ParseContext quoteParseContext = new ParseContext();
			Symbol toQuote = toSymbol(quoteParseContext, resourceName, (ParserRuleContext)quoteCtx.termValue().getChild(0));
			
			return Symbol.astInstruction(Instruction.TYPE_QUOTE, toQuote);
		}
		}
		
		return null;
	}
	
	public static <T extends ParserRuleContext> Symbol toSymbol(ParseContext parseContext, String resourceName, java.util.List<T> ctxs) {
		ArrayList<Symbol> sequence = new ArrayList<Symbol>();
		
		for(T ctx: ctxs) {
			Symbol item = toSymbol(parseContext, resourceName, ctx);
			sequence.add(item);
		}
		
		return Symbol.astGroup(sequence);
	}

	private static String replaceEscChars(String str) {
		return str.replace("\\r", "\r").replace("\\n", "\n");
	}
	
//	public static HLCodeBuilder toHLCodeBuilder(String resourceName, ParserRuleContext ctx) {
//		switch(ctx.getRuleIndex()) {
//		case giki.antlr4.GikiParser.RULE_expressions: {
//			ExpressionsContext expressionsCxt = (ExpressionsContext)ctx;
//			return toHLCodeBuilder(resourceName, expressionsCxt.expression());
//		}
//		case giki.antlr4.GikiParser.RULE_expression: {
//			ExpressionContext expressionContext = (ExpressionContext)ctx;
//			return toHLCodeBuilder(resourceName, (ParserRuleContext)expressionContext.getChild(0));
//		}
//		case giki.antlr4.GikiParser.RULE_variableDeclaration: {
//			VariableDeclarationContext variableDeclarationCxt = (VariableDeclarationContext)ctx;
//			
//			if(variableDeclarationCxt.isDeclaration == null) {
//				return toHLCodeBuilder(resourceName, (ParserRuleContext)variableDeclarationCxt.getChild(0));
//			} else {
//				String identifier = variableDeclarationCxt.ID().getText();
//				
//				if(variableDeclarationCxt.hasInitialization != null) {
//					HLCodeBuilder initialization = toHLCodeBuilder(resourceName, variableDeclarationCxt.value);
//					
//					return new HLVariableDeclaration(identifier, initialization);
//				} else {
//					return new HLVariableDeclaration(identifier);
//				}
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_assignment: {
//			AssignmentContext assignmentCtx = 
//				(AssignmentContext)ctx;
//			
//			if(assignmentCtx.isAssignment == null) {
//				return toHLCodeBuilder(resourceName, (ParserRuleContext)assignmentCtx.getChild(0));
//			} else {
//				String identifier = assignmentCtx.id.getText();
//				
//				HLCodeBuilder value = toHLCodeBuilder(resourceName, assignmentCtx.value);
//				
//				return new HLVariableAssignment(identifier, value);
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_decision: {
//			DecisionContext decisionContext = (DecisionContext)ctx;
//			
//			if(decisionContext.decisionTail().pipeline() == null || decisionContext.decisionTail().pipeline().isEmpty())
//				return toHLCodeBuilder(resourceName, decisionContext.value);
//			else {
//				HLDecision decision = new HLDecision();
//				
//				HLCodeBuilder firstAlternative = toHLCodeBuilder(resourceName, decisionContext.value);
//				
//				decision.alternative(firstAlternative);
//				
//				for(PipelineContext alternativeCtx: decisionContext.decisionTail().pipeline()) {
//					HLCodeBuilder alternative = toHLCodeBuilder(resourceName, alternativeCtx);
//					decision.alternative(alternative);
//				}
//				
//				return decision;
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_pipeline: {
//			PipelineContext pipelineContext = (PipelineContext)ctx;
//			
//			if(pipelineContext.hasFrom == null && pipelineContext.hasInters == null && pipelineContext.hasTo == null)
//				return toHLCodeBuilder(resourceName, pipelineContext.lhs);
//			else {
//				HLCodeBuilder codeBuilder;
//				
//				HLPipeline pipeline = new HLPipeline();
//
//				HLCodeBuilder from = null;
//				HLCodeBuilder to = null;
//				HLCodeBuilder firstPipe = toHLCodeBuilder(resourceName, pipelineContext.lhs);
//				
//				if(pipelineContext.hasFrom != null) {
//					from = firstPipe;
//					firstPipe = toHLCodeBuilder(resourceName, pipelineContext.hasFrom.body);
//				}
//				
//				if(pipelineContext.hasTo != null) {
//					to = toHLCodeBuilder(resourceName, pipelineContext.hasTo.body);
//				}
//				
//				pipeline.append(firstPipe);
//				
//				for(PipelineInterContext interCtx: pipelineContext.pipelineInter()) {
//					HLCodeBuilder pipe = toHLCodeBuilder(resourceName, interCtx.preFixOperation());
//					pipeline.append(pipe);
//				}
//				
//				codeBuilder = pipeline;
//				
//				if(to != null)
//					codeBuilder = new HLTo(to, codeBuilder);
//				
//				if(from != null)
//					codeBuilder = new HLFrom(from, codeBuilder);
//				
//				return new HLIOScope(codeBuilder);
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_preFixOperation: {
//			return toHLCodeBuilder(resourceName, (ParserRuleContext)ctx.getChild(0));
//		}
//		case giki.antlr4.GikiParser.RULE_preFixRest: {
//			PreFixRestContext preFixRestContext = (PreFixRestContext)ctx;
//			HLCodeBuilder toRepeatNode = toHLCodeBuilder(resourceName, preFixRestContext.expr);
//			
//			HLSequence sequence = new HLSequence()
//				.not(new HLInstruction(Instruction.TYPE_EOF))
//				.append(toRepeatNode);
//			
//			return new HLLoop(sequence);
//		}
//		case giki.antlr4.GikiParser.RULE_preFixFile: {
//			PreFixFileContext preFixFileCtx = (PreFixFileContext)ctx;
//			
//			HLCodeBuilder expr = toHLCodeBuilder(resourceName, preFixFileCtx.expr);
//			
//			return new HLPipeline()
//				.append(expr)
//				.append(new HLInstruction(Instruction.TYPE_FILE));
//		}
//		case giki.antlr4.GikiParser.RULE_compose: {
//			ComposeContext composeCtx = (ComposeContext)ctx;
//			
//			if(composeCtx.isCompose == null)
//				return toHLCodeBuilder(resourceName, (ParserRuleContext)composeCtx.lhs);
//			else {
//				HLCodeBuilder last = toHLCodeBuilder(resourceName, composeCtx.terms().get(composeCtx.terms().size() - 1));
//				HLCodeBuilder lastButOne = toHLCodeBuilder(resourceName, composeCtx.terms().get(composeCtx.terms().size() - 2));
//				
//				HLPipeline pipeline = new HLPipeline();
//				
//				pipeline.append(new HLSequence()
//					.append(last)
//					.append(lastButOne)
//				);
//				
//				for(int i = composeCtx.terms().size() - 3; i >= 0; i--) {
//					HLCodeBuilder part = toHLCodeBuilder(resourceName, composeCtx.terms().get(i));
//					pipeline.append(new HLSequence()
//						.append(new HLInstruction(Instruction.TYPE_MAP_UNION))
//						.append(part)
//					);
//				}
//				
//				pipeline.append(new HLInstruction(Instruction.TYPE_MAP_UNION));
//				
//				return pipeline;
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_interval: {
//			IntervalContext intervalCtx = (IntervalContext)ctx;
//			
//			Symbol lhsIdentifier = Symbol.getIdentifier(intervalCtx.lhs.PRODUCTION_STRING().getText());
//			Symbol rhsIdentifier = Symbol.getIdentifier(intervalCtx.rhs.PRODUCTION_STRING().getText());
//			
//			HLSequence sequence = new HLSequence();
//			
//			sequence.gte(lhsIdentifier);
//			sequence.lte(rhsIdentifier);
//			
//			return sequence;
//		}
//		case giki.antlr4.GikiParser.RULE_terms: {
//			TermsContext termsCtx = (TermsContext)ctx;
//			return toHLCodeBuilder(resourceName, termsCtx.term());
//		}
//		case giki.antlr4.GikiParser.RULE_term: {
//			TermContext termCtx = (TermContext)ctx;
//			
//			HLCodeBuilder term = toHLCodeBuilder(resourceName, (ParserRuleContext)termCtx.termValue().getChild(0));
//	
//			if(termCtx.lookupChain() != null) {
//				HLLookupChain lookupChain = new HLLookupChain(term);
//				
//				for(TerminalNode identifierNode: termCtx.lookupChain().ID()) {
//					String slotIdentifier = identifierNode.getText();
//					
//					lookupChain.append(slotIdentifier);
//				}
//				
//				term = lookupChain;
//			}
//			
//			if(termCtx.isNot != null)
//				term = new HLNot(term);
//			
//			if(termCtx.isMulti == null) {
//				return term;
//			} else {
//				return new HLLoop(term);
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_ioOperation: {
//			IoOperationContext ioOperationCtx = (IoOperationContext)ctx;
//			
//			HLInstruction instruction = toPrimitive((ParserRuleContext)ioOperationCtx.getChild(0));
//			
//			if(ioOperationCtx.IO_SCOPE() != null) {
//				int ioScope = Integer.parseInt(ioOperationCtx.IO_SCOPE().getText());
//				return new HLIOScopeUsage(instruction, ioScope);
//			} else {
//				return instruction;
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_identifier: {
//			IdentifierContext identifierCtx = (IdentifierContext)ctx;
//			
//			String identifier = identifierCtx.ID().get(0).getText();
//			for(int i = 1; i < identifierCtx.ID().size(); i++) {
//				identifier += "/" + identifierCtx.ID().get(i).getText();
//			}
//			
//			ResourceLocation resourceLocation = new ResourceLocation(
//				resourceName,
//				identifierCtx.ID().get(0).getSymbol().getLine(), 
//				identifierCtx.ID().get(0).getSymbol().getCharPositionInLine()
//			);
//			
////			return new HLIdentifier(identifier, resourceLocation);
//			return null;
//		}
//		case giki.antlr4.GikiParser.RULE_group: {
//			GroupContext groupContext = (GroupContext)ctx;
//			return toHLCodeBuilder(resourceName, groupContext.expressions().expression());
//		}
//		case giki.antlr4.GikiParser.RULE_list: {
//			ListContext semanticTermTransitionListContext = (ListContext)ctx;
//	
//			if(semanticTermTransitionListContext.expressions() != null) {
//				HLCodeBuilder initialization = toHLCodeBuilder(resourceName, semanticTermTransitionListContext.expressions().expression());
//				
//				return new HLList(initialization);
//			} else {
//				return new HLList(new HLSequence());
//			}
//		}
//		case giki.antlr4.GikiParser.RULE_map: {
//			MapContext mapCtx = (MapContext)ctx;
//			
//			HLMap map = new HLMap();
//	
//			int[] slots = new int[mapCtx.slotSet().size()];
//			
//			for(int i = 0; i < slots.length; i++) {
//				SlotSetContext slotSetCtx = mapCtx.slotSet().get(i);
//				String slotIdentifier = slotSetCtx.ID().getText();
//				HLCodeBuilder slotValue = toHLCodeBuilder(resourceName, slotSetCtx.value);
//				
//				map.setSlot(slotIdentifier, slotValue);
//			}
//			
//			return map;
//		}
//		case giki.antlr4.GikiParser.RULE_produceVerbatim: {
//			ProduceVerbatimContext produceVerbatimCtx = (ProduceVerbatimContext)ctx;
//			String text = produceVerbatimCtx.VERBATIM().getText().replaceAll("\\\\´", "\\´");
//	
//			HLSequence sequence = new HLSequence();
//			
//			for(int i = 0; i < text.length(); i++)
//				sequence.quote("" + text.charAt(i));
//			
//			return sequence;
//		}
//		}
//		
//		return null;
//	}
//	
//	
//	private static HLInstruction toPrimitive(ParserRuleContext patternCtx) {
//		switch(patternCtx.getRuleIndex()) {
//		case giki.antlr4.GikiParser.RULE_consumeString: {
//			String identifierText = replaceEscChars(patternCtx.getText());
//			Symbol identifier = Symbol.getIdentifier(identifierText);
//			return new HLInstruction(Instruction.TYPE_PEEK_EQUALS_CONSUME, identifier);
//		}
//		case giki.antlr4.GikiParser.RULE_produceString: {
//			String identifierText = replaceEscChars(patternCtx.getText());
//			Symbol identifier = Symbol.getIdentifier(identifierText);
//			return new HLInstruction(Instruction.TYPE_QUOTE, identifier);
//		}
//		case giki.antlr4.GikiParser.RULE_consumeInteger: {
//			String integerText = patternCtx.getText();
//			int integerValue = Integer.parseInt(integerText);
//			Symbol integer = Symbol.getInteger(integerValue);
//			return new HLInstruction(Instruction.TYPE_PEEK_EQUALS_CONSUME, integer);
//		}
//		case giki.antlr4.GikiParser.RULE_produceInteger: {
//			String integerText = patternCtx.getText();
//			int integerValue = Integer.parseInt(integerText);
//			Symbol integer = Symbol.getInteger(integerValue);
//			return new HLInstruction(Instruction.TYPE_QUOTE, integer);
//		}
//		case giki.antlr4.GikiParser.RULE_namedPrimitive: {
//			NamedPrimitiveContext namedPrimitiveCtx = (NamedPrimitiveContext)patternCtx;
//			TerminalNode namedPrimitiveToken = (TerminalNode)namedPrimitiveCtx.getChild(0);
//			
//			switch(namedPrimitiveToken.getSymbol().getType()) {
//			case giki.antlr4.GikiLexer.NAMED_PRIMITIVE_EOF:
//				return new HLInstruction(Instruction.TYPE_EOF);
//			}
//			
//			break;
//		}
//		case giki.antlr4.GikiParser.RULE_move: {
//			return new HLInstruction(Instruction.TYPE_MOVE);
//		}
//		case giki.antlr4.GikiParser.RULE_peek: {
//			return new HLInstruction(Instruction.TYPE_PEEK);
//		}
//		case giki.antlr4.GikiParser.RULE_next: {
//			return new HLInstruction(Instruction.TYPE_CONSUME);
//		}
//		}
//		
//		return null;
//	}
//	
//	public static <T extends ParserRuleContext> HLCodeBuilder toHLCodeBuilder(String resourceName, java.util.List<T> ctxs) {
//		HLSequence sequence = new HLSequence();
//		
//		for(T ctx: ctxs) {
//			HLCodeBuilder item = toHLCodeBuilder(resourceName, ctx);
//			sequence.append(item);
//		}
//		
//		return sequence;
//	}
	
//	private interface AstProcessor {
//		void setup(Symbol ast, HLCodeBuilder.Validation validation);
//		void validate(Symbol ast, HLCodeBuilder.Validation validation);
//		void appendTo(Symbol ast, CodeBuilder codeBuilder);
//	}
//	
//	private static Hashtable<Symbol.Identifier, AstProcessor> astProcessorMap;
//	
//	static {
//		astProcessorMap = new Hashtable<Symbol.Identifier, AstProcessor>();
//		
//		astProcessorMap.put(Symbol.Map.AST_TYPE_ASSIGNMENT, new AstProcessor() {
//			@Override
//			public void validate(Symbol ast, Validation validation) {
//				validate(value, validation);
//			}
//			
//			@Override
//			public void setup(Symbol ast, Validation validation) {
//				// Ensure variables are declared according a current default variable scope, such that naming conflicts are avoided across macros
//				setup(value, validation);
//			}
//			
//			@Override
//			public void appendTo(Symbol ast, CodeBuilder codeBuilder) {
//				codeBuilder.beginAssertion();
//				codeBuilder.setOutputVariable(identifier);
//				appendTo(value, codeBuilder);
//				codeBuilder.endAssertion();
//			}
//		});
//	}
//	
//	public static void setup(Symbol ast, HLCodeBuilder.Validation validation) {
//		
//	}
//	
//	public static void validate(Symbol ast, HLCodeBuilder.Validation validation) {
//		
//	}
}
