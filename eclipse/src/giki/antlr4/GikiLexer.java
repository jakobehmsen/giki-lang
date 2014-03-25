// Generated from C:\github\giki-lang\eclipse\src\giki\antlr4\Giki.g4 by ANTLR 4.1
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
		DOLLAR=1, COMMA=2, AT=3, HASH=4, DOUBLE_PLUS=5, CONSUMPTION_STRING=6, 
		PRODUCTION_STRING=7, CONSUMPTION_INT=8, PRODUCTION_INT=9, IO_SCOPE=10, 
		SINGLE_QUOTE=11, EXCLAMATION=12, CARET=13, PATH_SEPARATOR=14, DOUBLE_DOT=15, 
		SEMICOLON=16, NAMED_PRIMITIVE_RETURN=17, NAMED_PRIMITIVE_VARIABLES=18, 
		NAMED_PRIMITIVE_EOF=19, NAMED_PRIMITIVE_NIL=20, KEYWORD_DEFINE=21, KEYWORD_USE=22, 
		KEYWORD_FROM=23, KEYWORD_TO=24, KEYWORD_VAR=25, KEYWORD_REST=26, KEYWORD_GOTO=27, 
		KEYWORD_FILE=28, PIPELINE=29, PIPELINE_FROM=30, PIPELINE_TO=31, PIPE=32, 
		UNDERSCORE=33, ASTERIX=34, PRECEDENCE_OPERATOR=35, COLON=36, DOT=37, EQUALS=38, 
		OPEN_SQ_BRACKET=39, CLOSE_SQ_BRACKET=40, OPEN_PARENTHESIS=41, CLOSE_PARENTHESIS=42, 
		OPEN_BRACKET=43, CLOSE_BRACKET=44, KW_IF=45, ID=46, WS=47, SINGLELINE_COMMENT=48, 
		MULTI_LINE_COMMENT=49, VERBATIM=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'$'", "','", "'@'", "'#'", "'++'", "CONSUMPTION_STRING", "PRODUCTION_STRING", 
		"CONSUMPTION_INT", "PRODUCTION_INT", "IO_SCOPE", "'''", "'!'", "'^'", 
		"'::'", "'..'", "';'", "'ret'", "'vars'", "'eof'", "'nil'", "'def'", "'use'", 
		"'from'", "'to'", "'var'", "'rest'", "'goto'", "'file'", "PIPELINE", "PIPELINE_FROM", 
		"PIPELINE_TO", "'|'", "'_'", "'*'", "PRECEDENCE_OPERATOR", "':'", "'.'", 
		"'='", "'['", "']'", "'('", "')'", "'{'", "'}'", "'if'", "ID", "WS", "SINGLELINE_COMMENT", 
		"MULTI_LINE_COMMENT", "VERBATIM"
	};
	public static final String[] ruleNames = {
		"DOLLAR", "COMMA", "AT", "HASH", "DOUBLE_PLUS", "CONSUMPTION_STRING", 
		"PRODUCTION_STRING", "CONSUMPTION_INT", "PRODUCTION_INT", "STRING", "INT", 
		"IO_SCOPE", "SINGLE_QUOTE", "EXCLAMATION", "CARET", "TILDE", "PATH_SEPARATOR", 
		"DOUBLE_DOT", "SEMICOLON", "NAMED_PRIMITIVE_RETURN", "NAMED_PRIMITIVE_VARIABLES", 
		"NAMED_PRIMITIVE_EOF", "NAMED_PRIMITIVE_NIL", "KEYWORD_DEFINE", "KEYWORD_USE", 
		"KEYWORD_FROM", "KEYWORD_TO", "KEYWORD_VAR", "KEYWORD_REST", "KEYWORD_GOTO", 
		"KEYWORD_FILE", "GREATER_THAN", "LESS_THAN", "PIPELINE", "PIPELINE_FROM", 
		"PIPELINE_TO", "PIPE", "UNDERSCORE", "ASTERIX", "PRECEDENCE_OPERATOR", 
		"COLON", "DOT", "EQUALS", "OPEN_SQ_BRACKET", "CLOSE_SQ_BRACKET", "OPEN_PARENTHESIS", 
		"CLOSE_PARENTHESIS", "OPEN_BRACKET", "CLOSE_BRACKET", "ESC_CHAR", "DIGIT", 
		"KW_IF", "LETTER", "ID", "WS", "SINGLELINE_COMMENT", "MULTI_LINE_COMMENT", 
		"VERBATIM", "NESTED_VERBATIM", "VERBATIM_ESC_CHAR"
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
		case 5: CONSUMPTION_STRING_action((RuleContext)_localctx, actionIndex); break;

		case 6: PRODUCTION_STRING_action((RuleContext)_localctx, actionIndex); break;

		case 7: CONSUMPTION_INT_action((RuleContext)_localctx, actionIndex); break;

		case 9: STRING_action((RuleContext)_localctx, actionIndex); break;

		case 11: IO_SCOPE_action((RuleContext)_localctx, actionIndex); break;

		case 54: WS_action((RuleContext)_localctx, actionIndex); break;

		case 55: SINGLELINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 56: MULTI_LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 57: VERBATIM_action((RuleContext)_localctx, actionIndex); break;
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
	private void IO_SCOPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: setText(getText().substring(1)); break;
		}
	}
	private void SINGLELINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	private void VERBATIM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: setText(getText().substring(1, getText().length() - 1)); break;
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
	private void PRODUCTION_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: setText(getText().substring(1, getText().length() - 1)); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\64\u0162\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\7\13\u0097\n\13\f\13\16\13"+
		"\u009a\13\13\3\13\3\13\3\13\3\f\5\f\u00a0\n\f\3\f\6\f\u00a3\n\f\r\f\16"+
		"\f\u00a4\3\r\3\r\6\r\u00a9\n\r\r\r\16\r\u00aa\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3"+
		"%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\5)\u010a\n)\3*\3*\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3\64\3\64"+
		"\3\65\3\65\3\65\3\66\5\66\u0127\n\66\3\67\3\67\3\67\3\67\7\67\u012d\n"+
		"\67\f\67\16\67\u0130\13\67\38\68\u0133\n8\r8\168\u0134\38\38\39\39\39"+
		"\39\79\u013d\n9\f9\169\u0140\139\39\39\3:\3:\3:\3:\7:\u0148\n:\f:\16:"+
		"\u014b\13:\3:\3:\3:\3:\3:\3;\3;\3;\3<\3<\3<\3<\7<\u0159\n<\f<\16<\u015c"+
		"\13<\3<\3<\3=\3=\3=\4\u0149\u015a>\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b"+
		"\2\17\t\3\21\n\4\23\13\1\25\2\5\27\2\1\31\f\6\33\r\1\35\16\1\37\17\1!"+
		"\2\1#\20\1%\21\1\'\22\1)\23\1+\24\1-\25\1/\26\1\61\27\1\63\30\1\65\31"+
		"\1\67\32\19\33\1;\34\1=\35\1?\36\1A\2\1C\2\1E\37\1G \1I!\1K\"\1M#\1O$"+
		"\1Q%\1S&\1U\'\1W(\1Y)\1[*\1]+\1_,\1a-\1c.\1e\2\1g\2\1i/\1k\2\1m\60\1o"+
		"\61\bq\62\ts\63\nu\64\7w\2\1y\2\1\3\2\b\3\2$$\t\2$$^^ddhhppttvv\4\2C\\"+
		"c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\3\2\u00b6\u00b6\u0166\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2i\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2"+
		"\2u\3\2\2\2\3{\3\2\2\2\5}\3\2\2\2\7\177\3\2\2\2\t\u0081\3\2\2\2\13\u0083"+
		"\3\2\2\2\r\u0086\3\2\2\2\17\u008a\3\2\2\2\21\u008d\3\2\2\2\23\u0091\3"+
		"\2\2\2\25\u0093\3\2\2\2\27\u009f\3\2\2\2\31\u00a6\3\2\2\2\33\u00ae\3\2"+
		"\2\2\35\u00b0\3\2\2\2\37\u00b2\3\2\2\2!\u00b4\3\2\2\2#\u00b6\3\2\2\2%"+
		"\u00b9\3\2\2\2\'\u00bc\3\2\2\2)\u00be\3\2\2\2+\u00c2\3\2\2\2-\u00c7\3"+
		"\2\2\2/\u00cb\3\2\2\2\61\u00cf\3\2\2\2\63\u00d3\3\2\2\2\65\u00d7\3\2\2"+
		"\2\67\u00dc\3\2\2\29\u00df\3\2\2\2;\u00e3\3\2\2\2=\u00e8\3\2\2\2?\u00ed"+
		"\3\2\2\2A\u00f2\3\2\2\2C\u00f4\3\2\2\2E\u00f6\3\2\2\2G\u00f9\3\2\2\2I"+
		"\u00fd\3\2\2\2K\u0101\3\2\2\2M\u0103\3\2\2\2O\u0105\3\2\2\2Q\u0109\3\2"+
		"\2\2S\u010b\3\2\2\2U\u010d\3\2\2\2W\u010f\3\2\2\2Y\u0111\3\2\2\2[\u0113"+
		"\3\2\2\2]\u0115\3\2\2\2_\u0117\3\2\2\2a\u0119\3\2\2\2c\u011b\3\2\2\2e"+
		"\u011d\3\2\2\2g\u0120\3\2\2\2i\u0122\3\2\2\2k\u0126\3\2\2\2m\u0128\3\2"+
		"\2\2o\u0132\3\2\2\2q\u0138\3\2\2\2s\u0143\3\2\2\2u\u0151\3\2\2\2w\u0154"+
		"\3\2\2\2y\u015f\3\2\2\2{|\7&\2\2|\4\3\2\2\2}~\7.\2\2~\6\3\2\2\2\177\u0080"+
		"\7B\2\2\u0080\b\3\2\2\2\u0081\u0082\7%\2\2\u0082\n\3\2\2\2\u0083\u0084"+
		"\7-\2\2\u0084\u0085\7-\2\2\u0085\f\3\2\2\2\u0086\u0087\5!\21\2\u0087\u0088"+
		"\5\25\13\2\u0088\u0089\b\7\2\2\u0089\16\3\2\2\2\u008a\u008b\5\25\13\2"+
		"\u008b\u008c\b\b\3\2\u008c\20\3\2\2\2\u008d\u008e\5!\21\2\u008e\u008f"+
		"\5\27\f\2\u008f\u0090\b\t\4\2\u0090\22\3\2\2\2\u0091\u0092\5\27\f\2\u0092"+
		"\24\3\2\2\2\u0093\u0098\7$\2\2\u0094\u0097\5e\63\2\u0095\u0097\n\2\2\2"+
		"\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b"+
		"\u009c\7$\2\2\u009c\u009d\b\13\5\2\u009d\26\3\2\2\2\u009e\u00a0\7/\2\2"+
		"\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3"+
		"\5g\64\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\30\3\2\2\2\u00a6\u00a8\7^\2\2\u00a7\u00a9\5g\64\2"+
		"\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\r\6\2\u00ad\32\3\2\2\2\u00ae"+
		"\u00af\7)\2\2\u00af\34\3\2\2\2\u00b0\u00b1\7#\2\2\u00b1\36\3\2\2\2\u00b2"+
		"\u00b3\7`\2\2\u00b3 \3\2\2\2\u00b4\u00b5\7\u0080\2\2\u00b5\"\3\2\2\2\u00b6"+
		"\u00b7\7<\2\2\u00b7\u00b8\7<\2\2\u00b8$\3\2\2\2\u00b9\u00ba\7\60\2\2\u00ba"+
		"\u00bb\7\60\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7=\2\2\u00bd(\3\2\2\2\u00be"+
		"\u00bf\7t\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7v\2\2\u00c1*\3\2\2\2\u00c2"+
		"\u00c3\7x\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7u\2\2"+
		"\u00c6,\3\2\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca\7h\2"+
		"\2\u00ca.\3\2\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7"+
		"n\2\2\u00ce\60\3\2\2\2\u00cf\u00d0\7f\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2"+
		"\7h\2\2\u00d2\62\3\2\2\2\u00d3\u00d4\7w\2\2\u00d4\u00d5\7u\2\2\u00d5\u00d6"+
		"\7g\2\2\u00d6\64\3\2\2\2\u00d7\u00d8\7h\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da"+
		"\7q\2\2\u00da\u00db\7o\2\2\u00db\66\3\2\2\2\u00dc\u00dd\7v\2\2\u00dd\u00de"+
		"\7q\2\2\u00de8\3\2\2\2\u00df\u00e0\7x\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2"+
		"\7t\2\2\u00e2:\3\2\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6"+
		"\7u\2\2\u00e6\u00e7\7v\2\2\u00e7<\3\2\2\2\u00e8\u00e9\7i\2\2\u00e9\u00ea"+
		"\7q\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7q\2\2\u00ec>\3\2\2\2\u00ed\u00ee"+
		"\7h\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0\7n\2\2\u00f0\u00f1\7g\2\2\u00f1"+
		"@\3\2\2\2\u00f2\u00f3\7@\2\2\u00f3B\3\2\2\2\u00f4\u00f5\7>\2\2\u00f5D"+
		"\3\2\2\2\u00f6\u00f7\5A!\2\u00f7\u00f8\5A!\2\u00f8F\3\2\2\2\u00f9\u00fa"+
		"\5S*\2\u00fa\u00fb\5A!\2\u00fb\u00fc\5A!\2\u00fcH\3\2\2\2\u00fd\u00fe"+
		"\5A!\2\u00fe\u00ff\5A!\2\u00ff\u0100\5S*\2\u0100J\3\2\2\2\u0101\u0102"+
		"\7~\2\2\u0102L\3\2\2\2\u0103\u0104\7a\2\2\u0104N\3\2\2\2\u0105\u0106\7"+
		",\2\2\u0106P\3\2\2\2\u0107\u010a\5C\"\2\u0108\u010a\5A!\2\u0109\u0107"+
		"\3\2\2\2\u0109\u0108\3\2\2\2\u010aR\3\2\2\2\u010b\u010c\7<\2\2\u010cT"+
		"\3\2\2\2\u010d\u010e\7\60\2\2\u010eV\3\2\2\2\u010f\u0110\7?\2\2\u0110"+
		"X\3\2\2\2\u0111\u0112\7]\2\2\u0112Z\3\2\2\2\u0113\u0114\7_\2\2\u0114\\"+
		"\3\2\2\2\u0115\u0116\7*\2\2\u0116^\3\2\2\2\u0117\u0118\7+\2\2\u0118`\3"+
		"\2\2\2\u0119\u011a\7}\2\2\u011ab\3\2\2\2\u011b\u011c\7\177\2\2\u011cd"+
		"\3\2\2\2\u011d\u011e\7^\2\2\u011e\u011f\t\3\2\2\u011ff\3\2\2\2\u0120\u0121"+
		"\4\62;\2\u0121h\3\2\2\2\u0122\u0123\7k\2\2\u0123\u0124\7h\2\2\u0124j\3"+
		"\2\2\2\u0125\u0127\t\4\2\2\u0126\u0125\3\2\2\2\u0127l\3\2\2\2\u0128\u012e"+
		"\5k\66\2\u0129\u012d\5k\66\2\u012a\u012d\5g\64\2\u012b\u012d\5M\'\2\u012c"+
		"\u0129\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2"+
		"\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012fn\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0131\u0133\t\5\2\2\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\b8"+
		"\b\2\u0137p\3\2\2\2\u0138\u0139\7\61\2\2\u0139\u013a\7\61\2\2\u013a\u013e"+
		"\3\2\2\2\u013b\u013d\n\6\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e"+
		"\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2"+
		"\2\2\u0141\u0142\b9\t\2\u0142r\3\2\2\2\u0143\u0144\7\61\2\2\u0144\u0145"+
		"\7,\2\2\u0145\u0149\3\2\2\2\u0146\u0148\13\2\2\2\u0147\u0146\3\2\2\2\u0148"+
		"\u014b\3\2\2\2\u0149\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014c\3\2"+
		"\2\2\u014b\u0149\3\2\2\2\u014c\u014d\7,\2\2\u014d\u014e\7\61\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0150\b:\n\2\u0150t\3\2\2\2\u0151\u0152\5w<\2\u0152"+
		"\u0153\b;\7\2\u0153v\3\2\2\2\u0154\u015a\7b\2\2\u0155\u0159\5w<\2\u0156"+
		"\u0159\5y=\2\u0157\u0159\n\7\2\2\u0158\u0155\3\2\2\2\u0158\u0156\3\2\2"+
		"\2\u0158\u0157\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u015b\3\2\2\2\u015a\u0158"+
		"\3\2\2\2\u015b\u015d\3\2\2\2\u015c\u015a\3\2\2\2\u015d\u015e\7\u00b6\2"+
		"\2\u015ex\3\2\2\2\u015f\u0160\7^\2\2\u0160\u0161\7\u00b6\2\2\u0161z\3"+
		"\2\2\2\21\2\u0096\u0098\u009f\u00a4\u00aa\u0109\u0126\u012c\u012e\u0134"+
		"\u013e\u0149\u0158\u015a";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}