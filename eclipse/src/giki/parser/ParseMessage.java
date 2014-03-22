package giki.parser;

public class ParseMessage {
	public final ResourceLocation location;
	public final String text;
	
	public ParseMessage(ResourceLocation location, String text) {
		if(location == null)
			new String();
		
		this.location = location;
		this.text = text;
	}
}
