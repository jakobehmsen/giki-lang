package giki.runtime;

public interface Output {
	void put(Symbol symbol);
	
	void closeOutput();
}
