package giki;

public class Host extends Idag {
	public static final Host INSTANCE = new Host();
	
	private Host() {
		super(null);
	}
	
	public Idag newObject(Idag agent) {
		return new Idag(agent);
	}
	
	@Override
	public String toString() {
		return "Host";
	}
}
