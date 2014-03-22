package giki.runtime;

public interface Stream extends Input, Output {

	void pipe();

	Symbol reify();

}
