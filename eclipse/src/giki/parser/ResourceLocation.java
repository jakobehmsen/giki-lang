package giki.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ResourceLocation {
	public final String resourceName;
	public final int line;
	public final int column;
	
	public ResourceLocation(String resourceName, ParserRuleContext parserRuleContext) {
		this.resourceName = resourceName;
		this.line = parserRuleContext.getStart().getLine();
		this.column = parserRuleContext.getStart().getCharPositionInLine();
	}
	
	public ResourceLocation(String resourceName, Token token) {
		this.resourceName = resourceName;
		this.line = token.getLine();
		this.column = token.getCharPositionInLine();
	}
	
	public ResourceLocation(String resourceName, int line, int column) {
		this.resourceName = resourceName;
		this.line = line;
		this.column = column;
	}
}
