package giki;

import java.util.Hashtable;

/***
 * 
 * An Idag is an association between an identity and an agent.
 * The Java object itself (this) represent the identity;
 */
public class Idag {
	public final Idag agent;
	
	public Hashtable<Integer, Idag> table;
	
	public Idag(Idag agent) {
		this.agent = agent;
	}
	
	public Idag lookup(Int term) {
		if(table == null)
			return null;
		return table.get(term.value);
	}

	public void define(Int term, Idag meaning) {
		if(table == null)
			table = new Hashtable<Integer, Idag>();
		table.put(term.value, meaning);
	}
	
	@Override
	public String toString() {
		return super.toString().substring(9) + "\\" + agent.toString();
	}
}
