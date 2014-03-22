package giki.scheduling;

public interface Interruptable {
	public interface Listener {
		void started();
		void resumed();
		void stopped();
		void finished();
	}
	
	public interface Factory {
		Interruptable createInterruptable();
	}
	
	void addListener(Listener listener);
	void removeLister(Listener listener);
	void start();
	void stop();
	void resume();
	void finish();
	void doBefore(SyncPoint syncPoint);
}
