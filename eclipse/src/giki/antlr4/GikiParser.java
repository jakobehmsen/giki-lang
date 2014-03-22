// Generated from C:\Users\Jakob\Dropbox\Eclipse\workspace\Giki 0.0.4\src\giki\antlr4\Giki.g4 by ANTLR 4.1
package giki.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GikiParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, AT=2, HASH=3, DOUBLE_PLUS=4, CONSUMPTION_STRING=5, PRODUCTION_STRING=6, 
		CONSUMPTION_INT=7, PRODUCTION_INT=8, IO_SCOPE=9, SINGLE_QUOTE=10, EXCLAMATION=11, 
		CARET=12, PATH_SEPARATOR=13, DOUBLE_DOT=14, SEMICOLON=15, NAMED_PRIMITIVE_RETURN=16, 
		NAMED_PRIMITIVE_VARIABLES=17, NAMED_PRIMITIVE_EOF=18, NAMED_PRIMITIVE_NIL=19, 
		KEYWORD_DEFINE=20, KEYWORD_USE=21, KEYWORD_FROM=22, KEYWORD_TO=23, KEYWORD_VAR=24, 
		KEYWORD_REST=25, KEYWORD_GOTO=26, KEYWORD_FILE=27, PIPELINE=28, PIPELINE_FROM=29, 
		PIPELINE_TO=30, PIPE=31, UNDERSCORE=32, ASTERIX=33, PRECEDENCE_OPERATOR=34, 
		COLON=35, DOT=36, EQUALS=37, OPEN_SQ_BRACKET=38, CLOSE_SQ_BRACKET=39, 
		OPEN_PARENTHESIS=40, CLOSE_PARENTHESIS=41, OPEN_BRACKET=42, CLOSE_BRACKET=43, 
		KW_IF=44, ID=45, WS=46, SINGLELINE_COMMENT=47, MULTI_LINE_COMMENT=48, 
		VERBATIM=49;
	public static final String[] tokenNames = {
		"<INVALID>", "','", "'@'", "'#'", "'++'", "CONSUMPTION_STRING", "PRODUCTION_STRING", 
		"CONSUMPTION_INT", "PRODUCTION_INT", "IO_SCOPE", "'''", "'!'", "'^'", 
		"'::'", "'..'", "';'", "'ret'", "'vars'", "'eof'", "'nil'", "'def'", "'use'", 
		"'from'", "'to'", "'var'", "'rest'", "'goto'", "'file'", "PIPELINE", "PIPELINE_FROM", 
		"PIPELINE_TO", "'|'", "'_'", "'*'", "PRECEDENCE_OPERATOR", "':'", "'.'", 
		"'='", "'['", "']'", "'('", "')'", "'{'", "'}'", "'if'", "ID", "WS", "SINGLELINE_COMMENT", 
		"MULTI_LINE_COMMENT", "VERBATIM"
	};
	public static final int
		RULE_program = 0, RULE_programElements = 1, RULE_module = 2, RULE_moduleModifiers = 3, 
		RULE_expressions = 4, RULE_expression = 5, RULE_variableDeclaration = 6, 
		RULE_assignment = 7, RULE_decision = 8, RULE_decisionTail = 9, RULE_pipeline = 10, 
		RULE_pipelineFrom = 11, RULE_pipelineInter = 12, RULE_pipelineTo = 13, 
		RULE_preFixOperation = 14, RULE_preFixRest = 15, RULE_preFixFile = 16, 
		RULE_compose = 17, RULE_terms = 18, RULE_term = 19, RULE_termValue = 20, 
		RULE_lookupChain = 21, RULE_interval = 22, RULE_slotSet = 23, RULE_group = 24, 
		RULE_list = 25, RULE_map = 26, RULE_ioOperation = 27, RULE_identifier = 28, 
		RULE_matchID = 29, RULE_consumeString = 30, RULE_produceString = 31, RULE_consumeInteger = 32, 
		RULE_produceInteger = 33, RULE_namedPrimitive = 34, RULE_move = 35, RULE_peek = 36, 
		RULE_next = 37, RULE_label = 38, RULE_produceVerbatim = 39, RULE_quote = 40;
	public static final String[] ruleNames = {
		"program", "programElements", "module", "moduleModifiers", "expressions", 
		"expression", "variableDeclaration", "assignment", "decision", "decisionTail", 
		"pipeline", "pipelineFrom", "pipelineInter", "pipelineTo", "preFixOperation", 
		"preFixRest", "preFixFile", "compose", "terms", "term", "termValue", "lookupChain", 
		"interval", "slotSet", "group", "list", "map", "ioOperation", "identifier", 
		"matchID", "consumeString", "produceString", "consumeInteger", "produceInteger", 
		"namedPrimitive", "move", "peek", "next", "label", "produceVerbatim", 
		"quote"
	};

	@Override
	public String getGrammarFileName() { return "Giki.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public GikiParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public ProgramElementsContext programElements() {
			return getRuleContext(ProgramElementsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); programElements();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramElementsContext extends ParserRuleContext {
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ExpressionsContext expressions(int i) {
			return getRuleContext(ExpressionsContext.class,i);
		}
		public List<ExpressionsContext> expressions() {
			return getRuleContexts(ExpressionsContext.class);
		}
		public ProgramElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterProgramElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitProgramElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitProgramElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramElementsContext programElements() throws RecognitionException {
		ProgramElementsContext _localctx = new ProgramElementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << CONSUMPTION_STRING) | (1L << PRODUCTION_STRING) | (1L << CONSUMPTION_INT) | (1L << PRODUCTION_INT) | (1L << SINGLE_QUOTE) | (1L << EXCLAMATION) | (1L << CARET) | (1L << NAMED_PRIMITIVE_RETURN) | (1L << NAMED_PRIMITIVE_VARIABLES) | (1L << NAMED_PRIMITIVE_EOF) | (1L << NAMED_PRIMITIVE_NIL) | (1L << KEYWORD_DEFINE) | (1L << KEYWORD_VAR) | (1L << KEYWORD_REST) | (1L << KEYWORD_FILE) | (1L << UNDERSCORE) | (1L << OPEN_SQ_BRACKET) | (1L << OPEN_PARENTHESIS) | (1L << OPEN_BRACKET) | (1L << ID) | (1L << VERBATIM))) != 0)) {
				{
				setState(86);
				switch (_input.LA(1)) {
				case KEYWORD_DEFINE:
					{
					setState(84); module();
					}
					break;
				case AT:
				case CONSUMPTION_STRING:
				case PRODUCTION_STRING:
				case CONSUMPTION_INT:
				case PRODUCTION_INT:
				case SINGLE_QUOTE:
				case EXCLAMATION:
				case CARET:
				case NAMED_PRIMITIVE_RETURN:
				case NAMED_PRIMITIVE_VARIABLES:
				case NAMED_PRIMITIVE_EOF:
				case NAMED_PRIMITIVE_NIL:
				case KEYWORD_VAR:
				case KEYWORD_REST:
				case KEYWORD_FILE:
				case UNDERSCORE:
				case OPEN_SQ_BRACKET:
				case OPEN_PARENTHESIS:
				case OPEN_BRACKET:
				case ID:
				case VERBATIM:
					{
					setState(85); expressions();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public Token selector;
		public ProgramElementsContext programElements() {
			return getRuleContext(ProgramElementsContext.class,0);
		}
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public TerminalNode KEYWORD_DEFINE() { return getToken(GikiParser.KEYWORD_DEFINE, 0); }
		public ModuleModifiersContext moduleModifiers() {
			return getRuleContext(ModuleModifiersContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(GikiParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(GikiParser.OPEN_PARENTHESIS, 0); }
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_module);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(KEYWORD_DEFINE);
			setState(92); moduleModifiers();
			setState(93); ((ModuleContext)_localctx).selector = match(ID);
			{
			setState(94); match(OPEN_PARENTHESIS);
			setState(95); programElements();
			setState(96); match(CLOSE_PARENTHESIS);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleModifiersContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(GikiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GikiParser.ID, i);
		}
		public ModuleModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterModuleModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitModuleModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitModuleModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleModifiersContext moduleModifiers() throws RecognitionException {
		ModuleModifiersContext _localctx = new ModuleModifiersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_moduleModifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(98); match(ID);
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionsContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(GikiParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(GikiParser.SEMICOLON, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); expression();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(105); match(SEMICOLON);
				setState(107);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(106); expression();
					}
					break;
				}
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); variableDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public Token isDeclaration;
		public Token hasInitialization;
		public DecisionContext value;
		public TerminalNode EQUALS() { return getToken(GikiParser.EQUALS, 0); }
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public DecisionContext decision() {
			return getRuleContext(DecisionContext.class,0);
		}
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public TerminalNode KEYWORD_VAR() { return getToken(GikiParser.KEYWORD_VAR, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableDeclaration);
		int _la;
		try {
			setState(123);
			switch (_input.LA(1)) {
			case KEYWORD_VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); match(KEYWORD_VAR);
				setState(117); ((VariableDeclarationContext)_localctx).isDeclaration = match(ID);
				setState(120);
				_la = _input.LA(1);
				if (_la==EQUALS) {
					{
					setState(118); ((VariableDeclarationContext)_localctx).hasInitialization = match(EQUALS);
					setState(119); ((VariableDeclarationContext)_localctx).value = decision();
					}
				}

				}
				break;
			case AT:
			case CONSUMPTION_STRING:
			case PRODUCTION_STRING:
			case CONSUMPTION_INT:
			case PRODUCTION_INT:
			case SINGLE_QUOTE:
			case EXCLAMATION:
			case CARET:
			case NAMED_PRIMITIVE_RETURN:
			case NAMED_PRIMITIVE_VARIABLES:
			case NAMED_PRIMITIVE_EOF:
			case NAMED_PRIMITIVE_NIL:
			case KEYWORD_REST:
			case KEYWORD_FILE:
			case UNDERSCORE:
			case OPEN_SQ_BRACKET:
			case OPEN_PARENTHESIS:
			case OPEN_BRACKET:
			case ID:
			case VERBATIM:
				enterOuterAlt(_localctx, 2);
				{
				setState(122); assignment();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Token id;
		public Token isAssignment;
		public DecisionContext value;
		public TerminalNode EQUALS() { return getToken(GikiParser.EQUALS, 0); }
		public DecisionContext decision() {
			return getRuleContext(DecisionContext.class,0);
		}
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		try {
			setState(129);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125); ((AssignmentContext)_localctx).id = match(ID);
				setState(126); ((AssignmentContext)_localctx).isAssignment = match(EQUALS);
				setState(127); ((AssignmentContext)_localctx).value = decision();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128); decision();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecisionContext extends ParserRuleContext {
		public PipelineContext value;
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public DecisionTailContext decisionTail() {
			return getRuleContext(DecisionTailContext.class,0);
		}
		public DecisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decision; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterDecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitDecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitDecision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecisionContext decision() throws RecognitionException {
		DecisionContext _localctx = new DecisionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_decision);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); ((DecisionContext)_localctx).value = pipeline();
			setState(132); decisionTail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecisionTailContext extends ParserRuleContext {
		public List<TerminalNode> PIPE() { return getTokens(GikiParser.PIPE); }
		public PipelineContext pipeline(int i) {
			return getRuleContext(PipelineContext.class,i);
		}
		public TerminalNode PIPE(int i) {
			return getToken(GikiParser.PIPE, i);
		}
		public List<PipelineContext> pipeline() {
			return getRuleContexts(PipelineContext.class);
		}
		public DecisionTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decisionTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterDecisionTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitDecisionTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitDecisionTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecisionTailContext decisionTail() throws RecognitionException {
		DecisionTailContext _localctx = new DecisionTailContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_decisionTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPE) {
				{
				{
				setState(134); match(PIPE);
				setState(135); pipeline();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PipelineContext extends ParserRuleContext {
		public PreFixOperationContext lhs;
		public PipelineFromContext hasFrom;
		public PipelineInterContext hasInters;
		public PipelineToContext hasTo;
		public List<PipelineInterContext> pipelineInter() {
			return getRuleContexts(PipelineInterContext.class);
		}
		public PipelineFromContext pipelineFrom() {
			return getRuleContext(PipelineFromContext.class,0);
		}
		public PipelineInterContext pipelineInter(int i) {
			return getRuleContext(PipelineInterContext.class,i);
		}
		public PipelineToContext pipelineTo() {
			return getRuleContext(PipelineToContext.class,0);
		}
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public PipelineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipeline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPipeline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPipeline(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPipeline(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipelineContext pipeline() throws RecognitionException {
		PipelineContext _localctx = new PipelineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pipeline);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); ((PipelineContext)_localctx).lhs = preFixOperation();
			{
			setState(143);
			_la = _input.LA(1);
			if (_la==PIPELINE_FROM) {
				{
				setState(142); ((PipelineContext)_localctx).hasFrom = pipelineFrom();
				}
			}

			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPELINE) {
				{
				{
				setState(145); ((PipelineContext)_localctx).hasInters = pipelineInter();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			_la = _input.LA(1);
			if (_la==PIPELINE_TO) {
				{
				setState(151); ((PipelineContext)_localctx).hasTo = pipelineTo();
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PipelineFromContext extends ParserRuleContext {
		public PreFixOperationContext body;
		public TerminalNode PIPELINE_FROM() { return getToken(GikiParser.PIPELINE_FROM, 0); }
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public PipelineFromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipelineFrom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPipelineFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPipelineFrom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPipelineFrom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipelineFromContext pipelineFrom() throws RecognitionException {
		PipelineFromContext _localctx = new PipelineFromContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pipelineFrom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(PIPELINE_FROM);
			setState(155); ((PipelineFromContext)_localctx).body = preFixOperation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PipelineInterContext extends ParserRuleContext {
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public TerminalNode PIPELINE() { return getToken(GikiParser.PIPELINE, 0); }
		public PipelineInterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipelineInter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPipelineInter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPipelineInter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPipelineInter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipelineInterContext pipelineInter() throws RecognitionException {
		PipelineInterContext _localctx = new PipelineInterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pipelineInter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(PIPELINE);
			setState(158); preFixOperation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PipelineToContext extends ParserRuleContext {
		public PreFixOperationContext body;
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public TerminalNode PIPELINE_TO() { return getToken(GikiParser.PIPELINE_TO, 0); }
		public PipelineToContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipelineTo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPipelineTo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPipelineTo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPipelineTo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipelineToContext pipelineTo() throws RecognitionException {
		PipelineToContext _localctx = new PipelineToContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pipelineTo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(PIPELINE_TO);
			setState(161); ((PipelineToContext)_localctx).body = preFixOperation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreFixOperationContext extends ParserRuleContext {
		public PreFixRestContext preFixRest() {
			return getRuleContext(PreFixRestContext.class,0);
		}
		public PreFixFileContext preFixFile() {
			return getRuleContext(PreFixFileContext.class,0);
		}
		public ComposeContext compose() {
			return getRuleContext(ComposeContext.class,0);
		}
		public PreFixOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preFixOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPreFixOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPreFixOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPreFixOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreFixOperationContext preFixOperation() throws RecognitionException {
		PreFixOperationContext _localctx = new PreFixOperationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_preFixOperation);
		try {
			setState(166);
			switch (_input.LA(1)) {
			case KEYWORD_REST:
				enterOuterAlt(_localctx, 1);
				{
				setState(163); preFixRest();
				}
				break;
			case KEYWORD_FILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(164); preFixFile();
				}
				break;
			case AT:
			case CONSUMPTION_STRING:
			case PRODUCTION_STRING:
			case CONSUMPTION_INT:
			case PRODUCTION_INT:
			case SINGLE_QUOTE:
			case EXCLAMATION:
			case CARET:
			case NAMED_PRIMITIVE_RETURN:
			case NAMED_PRIMITIVE_VARIABLES:
			case NAMED_PRIMITIVE_EOF:
			case NAMED_PRIMITIVE_NIL:
			case UNDERSCORE:
			case OPEN_SQ_BRACKET:
			case OPEN_PARENTHESIS:
			case OPEN_BRACKET:
			case ID:
			case VERBATIM:
				enterOuterAlt(_localctx, 3);
				{
				setState(165); compose();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreFixRestContext extends ParserRuleContext {
		public PreFixOperationContext expr;
		public TerminalNode KEYWORD_REST() { return getToken(GikiParser.KEYWORD_REST, 0); }
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public PreFixRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preFixRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPreFixRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPreFixRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPreFixRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreFixRestContext preFixRest() throws RecognitionException {
		PreFixRestContext _localctx = new PreFixRestContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_preFixRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); match(KEYWORD_REST);
			setState(169); ((PreFixRestContext)_localctx).expr = preFixOperation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreFixFileContext extends ParserRuleContext {
		public PreFixOperationContext expr;
		public TerminalNode KEYWORD_FILE() { return getToken(GikiParser.KEYWORD_FILE, 0); }
		public PreFixOperationContext preFixOperation() {
			return getRuleContext(PreFixOperationContext.class,0);
		}
		public PreFixFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preFixFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPreFixFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPreFixFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPreFixFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreFixFileContext preFixFile() throws RecognitionException {
		PreFixFileContext _localctx = new PreFixFileContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_preFixFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(KEYWORD_FILE);
			setState(172); ((PreFixFileContext)_localctx).expr = preFixOperation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComposeContext extends ParserRuleContext {
		public TermsContext lhs;
		public Token isCompose;
		public List<TermsContext> terms() {
			return getRuleContexts(TermsContext.class);
		}
		public TermsContext terms(int i) {
			return getRuleContext(TermsContext.class,i);
		}
		public List<TerminalNode> HASH() { return getTokens(GikiParser.HASH); }
		public TerminalNode HASH(int i) {
			return getToken(GikiParser.HASH, i);
		}
		public ComposeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compose; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterCompose(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitCompose(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitCompose(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComposeContext compose() throws RecognitionException {
		ComposeContext _localctx = new ComposeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_compose);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); ((ComposeContext)_localctx).lhs = terms();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HASH) {
				{
				{
				setState(175); ((ComposeContext)_localctx).isCompose = match(HASH);
				setState(176); terms();
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermsContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitTerms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitTerms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermsContext terms() throws RecognitionException {
		TermsContext _localctx = new TermsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_terms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(183); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(182); term();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(185); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Token isNot;
		public Token isMulti;
		public TerminalNode ASTERIX() { return getToken(GikiParser.ASTERIX, 0); }
		public TerminalNode EXCLAMATION() { return getToken(GikiParser.EXCLAMATION, 0); }
		public TermValueContext termValue() {
			return getRuleContext(TermValueContext.class,0);
		}
		public LookupChainContext lookupChain() {
			return getRuleContext(LookupChainContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if (_la==EXCLAMATION) {
				{
				setState(187); ((TermContext)_localctx).isNot = match(EXCLAMATION);
				}
			}

			setState(190); termValue();
			{
			setState(192);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(191); lookupChain();
				}
			}

			setState(195);
			_la = _input.LA(1);
			if (_la==ASTERIX) {
				{
				setState(194); ((TermContext)_localctx).isMulti = match(ASTERIX);
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermValueContext extends ParserRuleContext {
		public ProduceVerbatimContext produceVerbatim() {
			return getRuleContext(ProduceVerbatimContext.class,0);
		}
		public QuoteContext quote() {
			return getRuleContext(QuoteContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public IoOperationContext ioOperation() {
			return getRuleContext(IoOperationContext.class,0);
		}
		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class,0);
		}
		public GroupContext group() {
			return getRuleContext(GroupContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TermValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterTermValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitTermValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitTermValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermValueContext termValue() throws RecognitionException {
		TermValueContext _localctx = new TermValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_termValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(197); interval();
				}
				break;

			case 2:
				{
				setState(198); ioOperation();
				}
				break;

			case 3:
				{
				setState(199); identifier();
				}
				break;

			case 4:
				{
				setState(200); group();
				}
				break;

			case 5:
				{
				setState(201); list();
				}
				break;

			case 6:
				{
				setState(202); map();
				}
				break;

			case 7:
				{
				setState(203); produceVerbatim();
				}
				break;

			case 8:
				{
				setState(204); quote();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LookupChainContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(GikiParser.DOT); }
		public List<TerminalNode> ID() { return getTokens(GikiParser.ID); }
		public TerminalNode DOT(int i) {
			return getToken(GikiParser.DOT, i);
		}
		public TerminalNode ID(int i) {
			return getToken(GikiParser.ID, i);
		}
		public LookupChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lookupChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterLookupChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitLookupChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitLookupChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LookupChainContext lookupChain() throws RecognitionException {
		LookupChainContext _localctx = new LookupChainContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_lookupChain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(207); match(DOT);
				setState(208); match(ID);
				}
				}
				setState(211); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervalContext extends ParserRuleContext {
		public ProduceStringContext lhs;
		public ProduceStringContext rhs;
		public TerminalNode DOUBLE_DOT() { return getToken(GikiParser.DOUBLE_DOT, 0); }
		public ProduceStringContext produceString(int i) {
			return getRuleContext(ProduceStringContext.class,i);
		}
		public List<ProduceStringContext> produceString() {
			return getRuleContexts(ProduceStringContext.class);
		}
		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_interval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); ((IntervalContext)_localctx).lhs = produceString();
			setState(214); match(DOUBLE_DOT);
			setState(215); ((IntervalContext)_localctx).rhs = produceString();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SlotSetContext extends ParserRuleContext {
		public ExpressionContext value;
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GikiParser.COLON, 0); }
		public SlotSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slotSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterSlotSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitSlotSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitSlotSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlotSetContext slotSet() throws RecognitionException {
		SlotSetContext _localctx = new SlotSetContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_slotSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); match(ID);
			setState(218); match(COLON);
			setState(219); ((SlotSetContext)_localctx).value = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(GikiParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(GikiParser.OPEN_PARENTHESIS, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(OPEN_PARENTHESIS);
			setState(222); expressions();
			setState(223); match(CLOSE_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public TerminalNode CLOSE_SQ_BRACKET() { return getToken(GikiParser.CLOSE_SQ_BRACKET, 0); }
		public TerminalNode OPEN_SQ_BRACKET() { return getToken(GikiParser.OPEN_SQ_BRACKET, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225); match(OPEN_SQ_BRACKET);
			setState(227);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << CONSUMPTION_STRING) | (1L << PRODUCTION_STRING) | (1L << CONSUMPTION_INT) | (1L << PRODUCTION_INT) | (1L << SINGLE_QUOTE) | (1L << EXCLAMATION) | (1L << CARET) | (1L << NAMED_PRIMITIVE_RETURN) | (1L << NAMED_PRIMITIVE_VARIABLES) | (1L << NAMED_PRIMITIVE_EOF) | (1L << NAMED_PRIMITIVE_NIL) | (1L << KEYWORD_VAR) | (1L << KEYWORD_REST) | (1L << KEYWORD_FILE) | (1L << UNDERSCORE) | (1L << OPEN_SQ_BRACKET) | (1L << OPEN_PARENTHESIS) | (1L << OPEN_BRACKET) | (1L << ID) | (1L << VERBATIM))) != 0)) {
				{
				setState(226); expressions();
				}
			}

			setState(229); match(CLOSE_SQ_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapContext extends ParserRuleContext {
		public List<SlotSetContext> slotSet() {
			return getRuleContexts(SlotSetContext.class);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(GikiParser.CLOSE_BRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(GikiParser.COMMA); }
		public SlotSetContext slotSet(int i) {
			return getRuleContext(SlotSetContext.class,i);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(GikiParser.OPEN_BRACKET, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GikiParser.COMMA, i);
		}
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitMap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(OPEN_BRACKET);
			setState(240);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(232); slotSet();
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(233); match(COMMA);
					setState(234); slotSet();
					}
					}
					setState(239);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(242); match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IoOperationContext extends ParserRuleContext {
		public NamedPrimitiveContext namedPrimitive() {
			return getRuleContext(NamedPrimitiveContext.class,0);
		}
		public ConsumeStringContext consumeString() {
			return getRuleContext(ConsumeStringContext.class,0);
		}
		public PeekContext peek() {
			return getRuleContext(PeekContext.class,0);
		}
		public TerminalNode IO_SCOPE() { return getToken(GikiParser.IO_SCOPE, 0); }
		public ConsumeIntegerContext consumeInteger() {
			return getRuleContext(ConsumeIntegerContext.class,0);
		}
		public NextContext next() {
			return getRuleContext(NextContext.class,0);
		}
		public MoveContext move() {
			return getRuleContext(MoveContext.class,0);
		}
		public ProduceStringContext produceString() {
			return getRuleContext(ProduceStringContext.class,0);
		}
		public ProduceIntegerContext produceInteger() {
			return getRuleContext(ProduceIntegerContext.class,0);
		}
		public IoOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ioOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterIoOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitIoOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitIoOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IoOperationContext ioOperation() throws RecognitionException {
		IoOperationContext _localctx = new IoOperationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ioOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			switch (_input.LA(1)) {
			case CONSUMPTION_STRING:
				{
				setState(244); consumeString();
				}
				break;
			case PRODUCTION_STRING:
				{
				setState(245); produceString();
				}
				break;
			case CONSUMPTION_INT:
				{
				setState(246); consumeInteger();
				}
				break;
			case PRODUCTION_INT:
				{
				setState(247); produceInteger();
				}
				break;
			case NAMED_PRIMITIVE_RETURN:
			case NAMED_PRIMITIVE_VARIABLES:
			case NAMED_PRIMITIVE_EOF:
			case NAMED_PRIMITIVE_NIL:
				{
				setState(248); namedPrimitive();
				}
				break;
			case CARET:
				{
				setState(249); move();
				}
				break;
			case AT:
				{
				setState(250); peek();
				}
				break;
			case UNDERSCORE:
				{
				setState(251); next();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(255);
			_la = _input.LA(1);
			if (_la==IO_SCOPE) {
				{
				setState(254); match(IO_SCOPE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode PATH_SEPARATOR(int i) {
			return getToken(GikiParser.PATH_SEPARATOR, i);
		}
		public List<TerminalNode> ID() { return getTokens(GikiParser.ID); }
		public List<TerminalNode> PATH_SEPARATOR() { return getTokens(GikiParser.PATH_SEPARATOR); }
		public TerminalNode ID(int i) {
			return getToken(GikiParser.ID, i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(ID);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PATH_SEPARATOR) {
				{
				{
				setState(258); match(PATH_SEPARATOR);
				setState(259); match(ID);
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchIDContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public MatchIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterMatchID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitMatchID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitMatchID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchIDContext matchID() throws RecognitionException {
		MatchIDContext _localctx = new MatchIDContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_matchID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConsumeStringContext extends ParserRuleContext {
		public TerminalNode CONSUMPTION_STRING() { return getToken(GikiParser.CONSUMPTION_STRING, 0); }
		public ConsumeStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_consumeString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterConsumeString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitConsumeString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitConsumeString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConsumeStringContext consumeString() throws RecognitionException {
		ConsumeStringContext _localctx = new ConsumeStringContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_consumeString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); match(CONSUMPTION_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProduceStringContext extends ParserRuleContext {
		public TerminalNode PRODUCTION_STRING() { return getToken(GikiParser.PRODUCTION_STRING, 0); }
		public ProduceStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_produceString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterProduceString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitProduceString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitProduceString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProduceStringContext produceString() throws RecognitionException {
		ProduceStringContext _localctx = new ProduceStringContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_produceString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(PRODUCTION_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConsumeIntegerContext extends ParserRuleContext {
		public TerminalNode CONSUMPTION_INT() { return getToken(GikiParser.CONSUMPTION_INT, 0); }
		public ConsumeIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_consumeInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterConsumeInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitConsumeInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitConsumeInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConsumeIntegerContext consumeInteger() throws RecognitionException {
		ConsumeIntegerContext _localctx = new ConsumeIntegerContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_consumeInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271); match(CONSUMPTION_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProduceIntegerContext extends ParserRuleContext {
		public TerminalNode PRODUCTION_INT() { return getToken(GikiParser.PRODUCTION_INT, 0); }
		public ProduceIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_produceInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterProduceInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitProduceInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitProduceInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProduceIntegerContext produceInteger() throws RecognitionException {
		ProduceIntegerContext _localctx = new ProduceIntegerContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_produceInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(PRODUCTION_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedPrimitiveContext extends ParserRuleContext {
		public TerminalNode NAMED_PRIMITIVE_EOF() { return getToken(GikiParser.NAMED_PRIMITIVE_EOF, 0); }
		public TerminalNode NAMED_PRIMITIVE_RETURN() { return getToken(GikiParser.NAMED_PRIMITIVE_RETURN, 0); }
		public TerminalNode NAMED_PRIMITIVE_NIL() { return getToken(GikiParser.NAMED_PRIMITIVE_NIL, 0); }
		public TerminalNode NAMED_PRIMITIVE_VARIABLES() { return getToken(GikiParser.NAMED_PRIMITIVE_VARIABLES, 0); }
		public NamedPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedPrimitive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterNamedPrimitive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitNamedPrimitive(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitNamedPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamedPrimitiveContext namedPrimitive() throws RecognitionException {
		NamedPrimitiveContext _localctx = new NamedPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_namedPrimitive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAMED_PRIMITIVE_RETURN) | (1L << NAMED_PRIMITIVE_VARIABLES) | (1L << NAMED_PRIMITIVE_EOF) | (1L << NAMED_PRIMITIVE_NIL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MoveContext extends ParserRuleContext {
		public TerminalNode CARET() { return getToken(GikiParser.CARET, 0); }
		public MoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_move; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitMove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(CARET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PeekContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(GikiParser.AT, 0); }
		public PeekContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_peek; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterPeek(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitPeek(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitPeek(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PeekContext peek() throws RecognitionException {
		PeekContext _localctx = new PeekContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_peek);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279); match(AT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NextContext extends ParserRuleContext {
		public TerminalNode UNDERSCORE() { return getToken(GikiParser.UNDERSCORE, 0); }
		public NextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_next; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterNext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitNext(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitNext(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NextContext next() throws RecognitionException {
		NextContext _localctx = new NextContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_next);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(UNDERSCORE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GikiParser.ID, 0); }
		public TerminalNode COLON() { return getToken(GikiParser.COLON, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283); match(ID);
			setState(284); match(COLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProduceVerbatimContext extends ParserRuleContext {
		public TerminalNode VERBATIM() { return getToken(GikiParser.VERBATIM, 0); }
		public ProduceVerbatimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_produceVerbatim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterProduceVerbatim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitProduceVerbatim(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitProduceVerbatim(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProduceVerbatimContext produceVerbatim() throws RecognitionException {
		ProduceVerbatimContext _localctx = new ProduceVerbatimContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_produceVerbatim);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); match(VERBATIM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuoteContext extends ParserRuleContext {
		public TerminalNode SINGLE_QUOTE() { return getToken(GikiParser.SINGLE_QUOTE, 0); }
		public TermValueContext termValue() {
			return getRuleContext(TermValueContext.class,0);
		}
		public QuoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).enterQuote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GikiListener ) ((GikiListener)listener).exitQuote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GikiVisitor ) return ((GikiVisitor<? extends T>)visitor).visitQuote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuoteContext quote() throws RecognitionException {
		QuoteContext _localctx = new QuoteContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_quote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288); match(SINGLE_QUOTE);
			setState(289); termValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\63\u0126\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\7\3Y\n\3\f\3\16\3\\\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\7\5"+
		"f\n\5\f\5\16\5i\13\5\3\6\3\6\3\6\5\6n\n\6\7\6p\n\6\f\6\16\6s\13\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\5\b{\n\b\3\b\5\b~\n\b\3\t\3\t\3\t\3\t\5\t\u0084\n"+
		"\t\3\n\3\n\3\n\3\13\3\13\7\13\u008b\n\13\f\13\16\13\u008e\13\13\3\f\3"+
		"\f\5\f\u0092\n\f\3\f\7\f\u0095\n\f\f\f\16\f\u0098\13\f\3\f\5\f\u009b\n"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u00a9"+
		"\n\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\7\23\u00b4\n\23\f\23"+
		"\16\23\u00b7\13\23\3\24\6\24\u00ba\n\24\r\24\16\24\u00bb\3\25\5\25\u00bf"+
		"\n\25\3\25\3\25\5\25\u00c3\n\25\3\25\5\25\u00c6\n\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\5\26\u00d0\n\26\3\27\3\27\6\27\u00d4\n\27\r\27"+
		"\16\27\u00d5\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\5\33\u00e6\n\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00ee"+
		"\n\34\f\34\16\34\u00f1\13\34\5\34\u00f3\n\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\5\35\u00ff\n\35\3\35\5\35\u0102\n\35\3\36\3"+
		"\36\3\36\7\36\u0107\n\36\f\36\16\36\u010a\13\36\3\37\3\37\3 \3 \3!\3!"+
		"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3*\3*\2"+
		"+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"HJLNPR\2\3\3\2\22\25\u0123\2T\3\2\2\2\4Z\3\2\2\2\6]\3\2\2\2\bg\3\2\2\2"+
		"\nj\3\2\2\2\ft\3\2\2\2\16}\3\2\2\2\20\u0083\3\2\2\2\22\u0085\3\2\2\2\24"+
		"\u008c\3\2\2\2\26\u008f\3\2\2\2\30\u009c\3\2\2\2\32\u009f\3\2\2\2\34\u00a2"+
		"\3\2\2\2\36\u00a8\3\2\2\2 \u00aa\3\2\2\2\"\u00ad\3\2\2\2$\u00b0\3\2\2"+
		"\2&\u00b9\3\2\2\2(\u00be\3\2\2\2*\u00cf\3\2\2\2,\u00d3\3\2\2\2.\u00d7"+
		"\3\2\2\2\60\u00db\3\2\2\2\62\u00df\3\2\2\2\64\u00e3\3\2\2\2\66\u00e9\3"+
		"\2\2\28\u00fe\3\2\2\2:\u0103\3\2\2\2<\u010b\3\2\2\2>\u010d\3\2\2\2@\u010f"+
		"\3\2\2\2B\u0111\3\2\2\2D\u0113\3\2\2\2F\u0115\3\2\2\2H\u0117\3\2\2\2J"+
		"\u0119\3\2\2\2L\u011b\3\2\2\2N\u011d\3\2\2\2P\u0120\3\2\2\2R\u0122\3\2"+
		"\2\2TU\5\4\3\2U\3\3\2\2\2VY\5\6\4\2WY\5\n\6\2XV\3\2\2\2XW\3\2\2\2Y\\\3"+
		"\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\5\3\2\2\2\\Z\3\2\2\2]^\7\26\2\2^_\5\b\5\2"+
		"_`\7/\2\2`a\7*\2\2ab\5\4\3\2bc\7+\2\2c\7\3\2\2\2df\7/\2\2ed\3\2\2\2fi"+
		"\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\t\3\2\2\2ig\3\2\2\2jq\5\f\7\2km\7\21\2\2"+
		"ln\5\f\7\2ml\3\2\2\2mn\3\2\2\2np\3\2\2\2ok\3\2\2\2ps\3\2\2\2qo\3\2\2\2"+
		"qr\3\2\2\2r\13\3\2\2\2sq\3\2\2\2tu\5\16\b\2u\r\3\2\2\2vw\7\32\2\2wz\7"+
		"/\2\2xy\7\'\2\2y{\5\22\n\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|~\5\20\t\2}v"+
		"\3\2\2\2}|\3\2\2\2~\17\3\2\2\2\177\u0080\7/\2\2\u0080\u0081\7\'\2\2\u0081"+
		"\u0084\5\22\n\2\u0082\u0084\5\22\n\2\u0083\177\3\2\2\2\u0083\u0082\3\2"+
		"\2\2\u0084\21\3\2\2\2\u0085\u0086\5\26\f\2\u0086\u0087\5\24\13\2\u0087"+
		"\23\3\2\2\2\u0088\u0089\7!\2\2\u0089\u008b\5\26\f\2\u008a\u0088\3\2\2"+
		"\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\25"+
		"\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0091\5\36\20\2\u0090\u0092\5\30\r"+
		"\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0096\3\2\2\2\u0093\u0095"+
		"\5\32\16\2\u0094\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2"+
		"\u0096\u0097\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009b"+
		"\5\34\17\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\27\3\2\2\2\u009c"+
		"\u009d\7\37\2\2\u009d\u009e\5\36\20\2\u009e\31\3\2\2\2\u009f\u00a0\7\36"+
		"\2\2\u00a0\u00a1\5\36\20\2\u00a1\33\3\2\2\2\u00a2\u00a3\7 \2\2\u00a3\u00a4"+
		"\5\36\20\2\u00a4\35\3\2\2\2\u00a5\u00a9\5 \21\2\u00a6\u00a9\5\"\22\2\u00a7"+
		"\u00a9\5$\23\2\u00a8\u00a5\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a7\3\2"+
		"\2\2\u00a9\37\3\2\2\2\u00aa\u00ab\7\33\2\2\u00ab\u00ac\5\36\20\2\u00ac"+
		"!\3\2\2\2\u00ad\u00ae\7\35\2\2\u00ae\u00af\5\36\20\2\u00af#\3\2\2\2\u00b0"+
		"\u00b5\5&\24\2\u00b1\u00b2\7\5\2\2\u00b2\u00b4\5&\24\2\u00b3\u00b1\3\2"+
		"\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"%\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00ba\5(\25\2\u00b9\u00b8\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\'\3"+
		"\2\2\2\u00bd\u00bf\7\r\2\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00c2\5*\26\2\u00c1\u00c3\5,\27\2\u00c2\u00c1\3\2"+
		"\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c6\7#\2\2\u00c5"+
		"\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6)\3\2\2\2\u00c7\u00d0\5.\30\2"+
		"\u00c8\u00d0\58\35\2\u00c9\u00d0\5:\36\2\u00ca\u00d0\5\62\32\2\u00cb\u00d0"+
		"\5\64\33\2\u00cc\u00d0\5\66\34\2\u00cd\u00d0\5P)\2\u00ce\u00d0\5R*\2\u00cf"+
		"\u00c7\3\2\2\2\u00cf\u00c8\3\2\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00ca\3\2"+
		"\2\2\u00cf\u00cb\3\2\2\2\u00cf\u00cc\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00ce\3\2\2\2\u00d0+\3\2\2\2\u00d1\u00d2\7&\2\2\u00d2\u00d4\7/\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6-\3\2\2\2\u00d7\u00d8\5@!\2\u00d8\u00d9\7\20\2\2\u00d9\u00da"+
		"\5@!\2\u00da/\3\2\2\2\u00db\u00dc\7/\2\2\u00dc\u00dd\7%\2\2\u00dd\u00de"+
		"\5\f\7\2\u00de\61\3\2\2\2\u00df\u00e0\7*\2\2\u00e0\u00e1\5\n\6\2\u00e1"+
		"\u00e2\7+\2\2\u00e2\63\3\2\2\2\u00e3\u00e5\7(\2\2\u00e4\u00e6\5\n\6\2"+
		"\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8"+
		"\7)\2\2\u00e8\65\3\2\2\2\u00e9\u00f2\7,\2\2\u00ea\u00ef\5\60\31\2\u00eb"+
		"\u00ec\7\3\2\2\u00ec\u00ee\5\60\31\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1\3"+
		"\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00ea\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f5\7-\2\2\u00f5\67\3\2\2\2\u00f6\u00ff\5> \2\u00f7\u00ff"+
		"\5@!\2\u00f8\u00ff\5B\"\2\u00f9\u00ff\5D#\2\u00fa\u00ff\5F$\2\u00fb\u00ff"+
		"\5H%\2\u00fc\u00ff\5J&\2\u00fd\u00ff\5L\'\2\u00fe\u00f6\3\2\2\2\u00fe"+
		"\u00f7\3\2\2\2\u00fe\u00f8\3\2\2\2\u00fe\u00f9\3\2\2\2\u00fe\u00fa\3\2"+
		"\2\2\u00fe\u00fb\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"\u0101\3\2\2\2\u0100\u0102\7\13\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3"+
		"\2\2\2\u01029\3\2\2\2\u0103\u0108\7/\2\2\u0104\u0105\7\17\2\2\u0105\u0107"+
		"\7/\2\2\u0106\u0104\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109;\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\7/\2\2\u010c"+
		"=\3\2\2\2\u010d\u010e\7\7\2\2\u010e?\3\2\2\2\u010f\u0110\7\b\2\2\u0110"+
		"A\3\2\2\2\u0111\u0112\7\t\2\2\u0112C\3\2\2\2\u0113\u0114\7\n\2\2\u0114"+
		"E\3\2\2\2\u0115\u0116\t\2\2\2\u0116G\3\2\2\2\u0117\u0118\7\16\2\2\u0118"+
		"I\3\2\2\2\u0119\u011a\7\4\2\2\u011aK\3\2\2\2\u011b\u011c\7\"\2\2\u011c"+
		"M\3\2\2\2\u011d\u011e\7/\2\2\u011e\u011f\7%\2\2\u011fO\3\2\2\2\u0120\u0121"+
		"\7\63\2\2\u0121Q\3\2\2\2\u0122\u0123\7\f\2\2\u0123\u0124\5*\26\2\u0124"+
		"S\3\2\2\2\34XZgmqz}\u0083\u008c\u0091\u0096\u009a\u00a8\u00b5\u00bb\u00be"+
		"\u00c2\u00c5\u00cf\u00d5\u00e5\u00ef\u00f2\u00fe\u0101\u0108";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}