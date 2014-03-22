package giki.runtime;

public interface Input {

	boolean atEnd();

	Symbol peek();

	Symbol consume();
	
	void closeInput();

	Symbol peek(int ahead);
}
