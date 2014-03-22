package giki.gui;

import giki.scheduling.Interruptable;
import giki.scheduling.Scheduler;

public interface TaskProvider {
	public Scheduler.TaskNode createTaskRoot();
	public void setup(Interruptable interruptable);
}
