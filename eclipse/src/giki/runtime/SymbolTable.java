package giki.runtime;

import java.util.Hashtable;

public class SymbolTable {
	private static Hashtable<String, Integer> stringToIntMap = new Hashtable<String, Integer>();
	private static Hashtable<Integer, String> intToStringMap = new Hashtable<Integer, String>();

	public static int getSymbolCode(String symbolStr) {
		return getSymbolCodeInteger(symbolStr);
	}
	
	public static int getSymbolCodeInteger(String symbolStr) {
		Integer symbolCode = stringToIntMap.get(symbolStr);
		
		if(symbolCode == null) {
			symbolCode = stringToIntMap.size();
			stringToIntMap.put(symbolStr, symbolCode);
			intToStringMap.put(symbolCode, symbolStr);
		}
		
		return symbolCode;
	}

	public static String getSymbolString(int symbolCode) {
		return intToStringMap.get(symbolCode);
	}
}
