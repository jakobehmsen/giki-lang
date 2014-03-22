package giki.scheduling;

public interface SyncPoint {
	void doAndThenCall(Runnable continuation);
}
