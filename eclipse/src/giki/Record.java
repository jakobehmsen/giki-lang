package giki;

import java.util.Map;

public class Record extends Idag {
	public final Map<String, Idag> map;

	public Record(Map<String, Idag> map) {
		super(Host.INSTANCE);
		
		this.map = map;
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}
