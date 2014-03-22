// Generated from C:\Users\Jakob\Dropbox\Eclipse\workspace\Giki 0.0.4\src\giki\antlr4\Giki.g4 by ANTLR 4.1
package giki.antlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GikiLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"','", "'@'", "'#'", "'++'", "CONSUMPTION_STRING", "PRODUCTION_STRING", 
		"CONSUMPTION_INT", "PRODUCTION_INT", "IO_SCOPE", "'''", "'!'", "'^'", 
		"'::'", "'..'", "';'", "'ret'", "'vars'", "'eof'", "'nil'", "'def'", "'use'", 
		"'from'", "'to'", "'var'", "'rest'", "'goto'", "'file'", "PIPELINE", "PIPELINE_FROM", 
		"PIPELINE_TO", "'|'", "'_'", "'*'", "PRECEDENCE_OPERATOR", "':'", "'.'", 
		"'='", "'['", "']'", "'('", "')'", "'{'", "'}'", "'if'", "ID", "WS", "SINGLELINE_COMMENT", 
		"MULTI_LINE_COMMENT", "VERBATIM"
	};
	public static final String[] ruleNames = {
		"COMMA", "AT", "HASH", "DOUBLE_PLUS", "CONSUMPTION_STRING", "PRODUCTION_STRING", 
		"CONSUMPTION_INT", "PRODUCTION_INT", "STRING", "INT", "IO_SCOPE", "SINGLE_QUOTE", 
		"EXCLAMATION", "CARET", "TILDE", "PATH_SEPARATOR", "DOUBLE_DOT", "SEMICOLON", 
		"NAMED_PRIMITIVE_RETURN", "NAMED_PRIMITIVE_VARIABLES", "NAMED_PRIMITIVE_EOF", 
		"NAMED_PRIMITIVE_NIL", "KEYWORD_DEFINE", "KEYWORD_USE", "KEYWORD_FROM", 
		"KEYWORD_TO", "KEYWORD_VAR", "KEYWORD_REST", "KEYWORD_GOTO", "KEYWORD_FILE", 
		"GREATER_THAN", "LESS_THAN", "PIPELINE", "PIPELINE_FROM", "PIPELINE_TO", 
		"PIPE", "UNDERSCORE", "ASTERIX", "PRECEDENCE_OPERATOR", "COLON", "DOT", 
		"EQUALS", "OPEN_SQ_BRACKET", "CLOSE_SQ_BRACKET", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "ESC_CHAR", "DIGIT", "KW_IF", "LETTER", 
		"ID", "WS", "SINGLELINE_COMMENT", "MULTI_LINE_COMMENT", "VERBATIM", "NESTED_VERBATIM", 
		"VERBATIM_ESC_CHAR"
	};


	public GikiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Giki.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 4: CONSUMPTION_STRING_action((RuleContext)_localctx, actionIndex); break;

		case 5: PRODUCTION_STRING_action((RuleContext)_localctx, actionIndex); break;

		case 6: CONSUMPTION_INT_action((RuleContext)_localctx, actionIndex); break;

		case 8: STRING_action((RuleContext)_localctx, actionIndex); break;

		case 10: IO_SCOPE_action((RuleContext)_localctx, actionIndex); break;

		case 53: WS_action((RuleContext)_localctx, actionIndex); break;

		case 54: SINGLELINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 55: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 56: VERBATIM_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void IO_SCOPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: setText(getText().substring(1)); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: skip();  break;
		}
	}
	private void VERBATIM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: setText(getText().substring(1, getText().length() - 1)); break;
		}
	}
	private void PRODUCTION_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: setText(getText().substring(1, getText().length() - 1)); break;
		}
	}
	private void SINGLELINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	private void CONSUMPTION_INT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: setText(getText().substring(1)); break;
		}
	}
	private void MULTI_LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: skip();  break;
		}
	}
	private void CONSUMPTION_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: setText(getText().substring(2, getText().length() - 1)); break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: setText(getText().substring(1, getText().length() - 1)); break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\63\u015e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\n\7\n\u0093\n\n\f\n\16\n\u0096\13\n\3\n\3\n\3"+
		"\n\3\13\5\13\u009c\n\13\3\13\6\13\u009f\n\13\r\13\16\13\u00a0\3\f\3\f"+
		"\6\f\u00a5\n\f\r\f\16\f\u00a6\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3"+
		"$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\5(\u0106\n(\3)\3)\3*\3*\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\64\3\65\5\65\u0123\n\65\3\66\3\66\3\66\3\66\7\66\u0129\n\66\f\66\16"+
		"\66\u012c\13\66\3\67\6\67\u012f\n\67\r\67\16\67\u0130\3\67\3\67\38\38"+
		"\38\38\78\u0139\n8\f8\168\u013c\138\38\38\39\39\39\39\79\u0144\n9\f9\16"+
		"9\u0147\139\39\39\39\39\39\3:\3:\3:\3;\3;\3;\3;\7;\u0155\n;\f;\16;\u0158"+
		"\13;\3;\3;\3<\3<\3<\4\u0145\u0156=\3\3\1\5\4\1\7\5\1\t\6\1\13\7\2\r\b"+
		"\3\17\t\4\21\n\1\23\2\5\25\2\1\27\13\6\31\f\1\33\r\1\35\16\1\37\2\1!\17"+
		"\1#\20\1%\21\1\'\22\1)\23\1+\24\1-\25\1/\26\1\61\27\1\63\30\1\65\31\1"+
		"\67\32\19\33\1;\34\1=\35\1?\2\1A\2\1C\36\1E\37\1G \1I!\1K\"\1M#\1O$\1"+
		"Q%\1S&\1U\'\1W(\1Y)\1[*\1]+\1_,\1a-\1c\2\1e\2\1g.\1i\2\1k/\1m\60\bo\61"+
		"\tq\62\ns\63\7u\2\1w\2\1\3\2\b\3\2$$\t\2$$^^ddhhppttvv\4\2C\\c|\5\2\13"+
		"\f\17\17\"\"\4\2\f\f\17\17\3\2\u00b6\u00b6\u0162\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2g\3\2"+
		"\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3y\3\2\2\2"+
		"\5{\3\2\2\2\7}\3\2\2\2\t\177\3\2\2\2\13\u0082\3\2\2\2\r\u0086\3\2\2\2"+
		"\17\u0089\3\2\2\2\21\u008d\3\2\2\2\23\u008f\3\2\2\2\25\u009b\3\2\2\2\27"+
		"\u00a2\3\2\2\2\31\u00aa\3\2\2\2\33\u00ac\3\2\2\2\35\u00ae\3\2\2\2\37\u00b0"+
		"\3\2\2\2!\u00b2\3\2\2\2#\u00b5\3\2\2\2%\u00b8\3\2\2\2\'\u00ba\3\2\2\2"+
		")\u00be\3\2\2\2+\u00c3\3\2\2\2-\u00c7\3\2\2\2/\u00cb\3\2\2\2\61\u00cf"+
		"\3\2\2\2\63\u00d3\3\2\2\2\65\u00d8\3\2\2\2\67\u00db\3\2\2\29\u00df\3\2"+
		"\2\2;\u00e4\3\2\2\2=\u00e9\3\2\2\2?\u00ee\3\2\2\2A\u00f0\3\2\2\2C\u00f2"+
		"\3\2\2\2E\u00f5\3\2\2\2G\u00f9\3\2\2\2I\u00fd\3\2\2\2K\u00ff\3\2\2\2M"+
		"\u0101\3\2\2\2O\u0105\3\2\2\2Q\u0107\3\2\2\2S\u0109\3\2\2\2U\u010b\3\2"+
		"\2\2W\u010d\3\2\2\2Y\u010f\3\2\2\2[\u0111\3\2\2\2]\u0113\3\2\2\2_\u0115"+
		"\3\2\2\2a\u0117\3\2\2\2c\u0119\3\2\2\2e\u011c\3\2\2\2g\u011e\3\2\2\2i"+
		"\u0122\3\2\2\2k\u0124\3\2\2\2m\u012e\3\2\2\2o\u0134\3\2\2\2q\u013f\3\2"+
		"\2\2s\u014d\3\2\2\2u\u0150\3\2\2\2w\u015b\3\2\2\2yz\7.\2\2z\4\3\2\2\2"+
		"{|\7B\2\2|\6\3\2\2\2}~\7%\2\2~\b\3\2\2\2\177\u0080\7-\2\2\u0080\u0081"+
		"\7-\2\2\u0081\n\3\2\2\2\u0082\u0083\5\37\20\2\u0083\u0084\5\23\n\2\u0084"+
		"\u0085\b\6\2\2\u0085\f\3\2\2\2\u0086\u0087\5\23\n\2\u0087\u0088\b\7\3"+
		"\2\u0088\16\3\2\2\2\u0089\u008a\5\37\20\2\u008a\u008b\5\25\13\2\u008b"+
		"\u008c\b\b\4\2\u008c\20\3\2\2\2\u008d\u008e\5\25\13\2\u008e\22\3\2\2\2"+
		"\u008f\u0094\7$\2\2\u0090\u0093\5c\62\2\u0091\u0093\n\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7$"+
		"\2\2\u0098\u0099\b\n\5\2\u0099\24\3\2\2\2\u009a\u009c\7/\2\2\u009b\u009a"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009f\5e\63\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\26\3\2\2\2\u00a2\u00a4\7^\2\2\u00a3\u00a5\5e\63\2\u00a4\u00a3"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\b\f\6\2\u00a9\30\3\2\2\2\u00aa\u00ab\7)\2\2"+
		"\u00ab\32\3\2\2\2\u00ac\u00ad\7#\2\2\u00ad\34\3\2\2\2\u00ae\u00af\7`\2"+
		"\2\u00af\36\3\2\2\2\u00b0\u00b1\7\u0080\2\2\u00b1 \3\2\2\2\u00b2\u00b3"+
		"\7<\2\2\u00b3\u00b4\7<\2\2\u00b4\"\3\2\2\2\u00b5\u00b6\7\60\2\2\u00b6"+
		"\u00b7\7\60\2\2\u00b7$\3\2\2\2\u00b8\u00b9\7=\2\2\u00b9&\3\2\2\2\u00ba"+
		"\u00bb\7t\2\2\u00bb\u00bc\7g\2\2\u00bc\u00bd\7v\2\2\u00bd(\3\2\2\2\u00be"+
		"\u00bf\7x\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7t\2\2\u00c1\u00c2\7u\2\2"+
		"\u00c2*\3\2\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7h\2"+
		"\2\u00c6,\3\2\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7"+
		"n\2\2\u00ca.\3\2\2\2\u00cb\u00cc\7f\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce"+
		"\7h\2\2\u00ce\60\3\2\2\2\u00cf\u00d0\7w\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2"+
		"\7g\2\2\u00d2\62\3\2\2\2\u00d3\u00d4\7h\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6"+
		"\7q\2\2\u00d6\u00d7\7o\2\2\u00d7\64\3\2\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da"+
		"\7q\2\2\u00da\66\3\2\2\2\u00db\u00dc\7x\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de"+
		"\7t\2\2\u00de8\3\2\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2"+
		"\7u\2\2\u00e2\u00e3\7v\2\2\u00e3:\3\2\2\2\u00e4\u00e5\7i\2\2\u00e5\u00e6"+
		"\7q\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7q\2\2\u00e8<\3\2\2\2\u00e9\u00ea"+
		"\7h\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7n\2\2\u00ec\u00ed\7g\2\2\u00ed"+
		">\3\2\2\2\u00ee\u00ef\7@\2\2\u00ef@\3\2\2\2\u00f0\u00f1\7>\2\2\u00f1B"+
		"\3\2\2\2\u00f2\u00f3\5? \2\u00f3\u00f4\5? \2\u00f4D\3\2\2\2\u00f5\u00f6"+
		"\5Q)\2\u00f6\u00f7\5? \2\u00f7\u00f8\5? \2\u00f8F\3\2\2\2\u00f9\u00fa"+
		"\5? \2\u00fa\u00fb\5? \2\u00fb\u00fc\5Q)\2\u00fcH\3\2\2\2\u00fd\u00fe"+
		"\7~\2\2\u00feJ\3\2\2\2\u00ff\u0100\7a\2\2\u0100L\3\2\2\2\u0101\u0102\7"+
		",\2\2\u0102N\3\2\2\2\u0103\u0106\5A!\2\u0104\u0106\5? \2\u0105\u0103\3"+
		"\2\2\2\u0105\u0104\3\2\2\2\u0106P\3\2\2\2\u0107\u0108\7<\2\2\u0108R\3"+
		"\2\2\2\u0109\u010a\7\60\2\2\u010aT\3\2\2\2\u010b\u010c\7?\2\2\u010cV\3"+
		"\2\2\2\u010d\u010e\7]\2\2\u010eX\3\2\2\2\u010f\u0110\7_\2\2\u0110Z\3\2"+
		"\2\2\u0111\u0112\7*\2\2\u0112\\\3\2\2\2\u0113\u0114\7+\2\2\u0114^\3\2"+
		"\2\2\u0115\u0116\7}\2\2\u0116`\3\2\2\2\u0117\u0118\7\177\2\2\u0118b\3"+
		"\2\2\2\u0119\u011a\7^\2\2\u011a\u011b\t\3\2\2\u011bd\3\2\2\2\u011c\u011d"+
		"\4\62;\2\u011df\3\2\2\2\u011e\u011f\7k\2\2\u011f\u0120\7h\2\2\u0120h\3"+
		"\2\2\2\u0121\u0123\t\4\2\2\u0122\u0121\3\2\2\2\u0123j\3\2\2\2\u0124\u012a"+
		"\5i\65\2\u0125\u0129\5i\65\2\u0126\u0129\5e\63\2\u0127\u0129\5K&\2\u0128"+
		"\u0125\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0127\3\2\2\2\u0129\u012c\3\2"+
		"\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012bl\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d\u012f\t\5\2\2\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\b\67"+
		"\b\2\u0133n\3\2\2\2\u0134\u0135\7\61\2\2\u0135\u0136\7\61\2\2\u0136\u013a"+
		"\3\2\2\2\u0137\u0139\n\6\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u013a\3\2"+
		"\2\2\u013d\u013e\b8\t\2\u013ep\3\2\2\2\u013f\u0140\7\61\2\2\u0140\u0141"+
		"\7,\2\2\u0141\u0145\3\2\2\2\u0142\u0144\13\2\2\2\u0143\u0142\3\2\2\2\u0144"+
		"\u0147\3\2\2\2\u0145\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0148\3\2"+
		"\2\2\u0147\u0145\3\2\2\2\u0148\u0149\7,\2\2\u0149\u014a\7\61\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\b9\n\2\u014cr\3\2\2\2\u014d\u014e\5u;\2\u014e"+
		"\u014f\b:\7\2\u014ft\3\2\2\2\u0150\u0156\7b\2\2\u0151\u0155\5u;\2\u0152"+
		"\u0155\5w<\2\u0153\u0155\n\7\2\2\u0154\u0151\3\2\2\2\u0154\u0152\3\2\2"+
		"\2\u0154\u0153\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0157\3\2\2\2\u0156\u0154"+
		"\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015a\7\u00b6\2"+
		"\2\u015av\3\2\2\2\u015b\u015c\7^\2\2\u015c\u015d\7\u00b6\2\2\u015dx\3"+
		"\2\2\2\21\2\u0092\u0094\u009b\u00a0\u00a6\u0105\u0122\u0128\u012a\u0130"+
		"\u013a\u0145\u0154\u0156";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}