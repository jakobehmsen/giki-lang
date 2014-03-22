package giki.diagnostics;

import java.io.PrintStream;

public class StopWatch {
	private long elapsed;
	private long start;
	private boolean running;
	
	public void start() {
		start = System.nanoTime();
		running = true;
	}
	
	public void stop() {
		long end = System.nanoTime();
		
		elapsed += end - start;
		
		running = false;
	}
	
	public void reset() {
		elapsed = 0;
		start = -1;
		running = false;
	}
	
	public long elapsedSinceLastStart() {
		if(running) {
			long end = System.nanoTime();
			
			return end - start;
		} else
			return 0;
	}
	
	public long elapsed() {
		return elapsed + elapsedSinceLastStart();
	}
	
	public void printElapsed(PrintStream out, String name) {
		out.println(name + ": " + elapsed() / 1000000.0 + " millis.");
	}
}
