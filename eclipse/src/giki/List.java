package giki;

import java.util.Arrays;

public class List extends Idag {
	public final Idag[] items;

	public List(Idag[] items) {
		super(Host.INSTANCE);
		
		this.items = items;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(items);
	}
}
