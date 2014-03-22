package giki.runtime;

import java.util.ArrayList;
import java.util.List;

public class IOBuffer implements Stream {
//	private int inputStart;
//	private int outputStart; // = inputEnd
//	private int outputEnd;
	
	private int firstIndex;
	private int lastIndex;
	private int maxIndex = -1;
	private List<Symbol> list;
//	private int limit;
	
	public IOBuffer() {
		this(new ArrayList<Symbol>());
	}
	
	public IOBuffer(CharSequence chars) {
		this.list = new ArrayList<Symbol>();
		for(int i = 0; i < chars.length(); i++) {
			char ch = chars.charAt(i);
			list.add(Symbol.getIdentifier("" + ch));
		}
		lastIndex = list.size();
	}
	
	public IOBuffer(List<Symbol> list) {
		this.list = list;
		lastIndex = list.size();
	}

	@Override
	public void put(Symbol symbol) {
		if(maxIndex != -1 && list.size() >= maxIndex)
			return;
		
		list.add(symbol);
//		if(lastIndex >= list.size())
//			list.add(symbol);
//		else {
//			list.set(lastIndex, symbol);
//		}
//		lastIndex++;
	}

//	@Override
//	public Input asInput() {
////		outerIntervals.push(firstIndex);
////		outerIntervals.push(lastIndex);
//		
//		firstIndex = lastIndex;
//		lastIndex = list.size();
//		
//		return this;
//	}

	public void pipe() {
		firstIndex = lastIndex;
		lastIndex = list.size();
	}

	@Override
	public boolean atEnd() {
		return firstIndex >= list.size();
	}

	@Override
	public Symbol peek() {
		return firstIndex < lastIndex ? list.get(firstIndex) : null;
	}

	@Override
	public Symbol consume() {
		if(firstIndex < lastIndex) {
			firstIndex++;
			return list.get(firstIndex - 1);
		}
		return null;
	}
	
//	private Stack<Integer> outerIntervals = new Stack<Integer>();

	public void openOutput() {
//		outerIntervals.push(firstIndex);
//		outerIntervals.push(lastIndex);
//		
//		firstIndex = lastIndex;
	}

	@Override
	public void closeOutput() {
//		lastIndex = outerIntervals.pop();
//		firstIndex = outerIntervals.pop();
	}

	@Override
	public void closeInput() {
	}
	
	@Override
	public String toString() {
//		return list.subList(firstIndex, lastIndex).toString();
		
		String in = firstIndex < lastIndex ? list.subList(firstIndex, lastIndex).toString() : "";
		String out = lastIndex < list.size() ? list.subList(lastIndex, list.size()).toString() : "";
		
		if(in.length() > 0 && out.length() < 0)
			return "in=" + in + ",out=" + out;
		else if(in.length() > 0)
			return in;
		else if(out.length() > 0)
			return out;
		return "[]";
	}

	@Override
	public Symbol peek(int ahead) {
		int index = firstIndex + ahead;
		return index < lastIndex ? list.get(index) : null;
	}

//	@Override
//	public void setLimit(int limit) {
//		maxIndex = list.size() + limit;
//	}
	
	public Container.Default reify() {
		return new Container.Default(list);
	}
}
