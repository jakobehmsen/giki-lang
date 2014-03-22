package giki.scheduling;

import java.util.ArrayList;
import java.util.HashSet;

public class Scheduler {
	public interface TaskNode {
		TaskNode next(boolean finishRequested);
		boolean atEnd();
		
		public static class End implements TaskNode {
			public static final End INSTANCE = new End();
			
			private End() { }
			
			@Override
			public TaskNode next(boolean finishRequested) {
				return null;
			}

			@Override
			public boolean atEnd() {
				return true;
			}
		}
	}
	
	private static class ScheduledTask implements Interruptable {
		private static final int STATE_UNSTARTED = 0;
		private static final int STATE_EXECUTING = 1;
		private static final int STATE_PAUSED = 2;
		private static final int STATE_FINISHED = 3;
		
		public Scheduler scheduler;
		public int state;
		public TaskNode taskNode;
		public ArrayList<Interruptable.Listener> listeners = new ArrayList<Interruptable.Listener>();
		
		public ScheduledTask(Scheduler scheduler, TaskNode task) {
			state = STATE_UNSTARTED;
			this.scheduler = scheduler;
			this.taskNode = task;
		}

		@Override
		public void addListener(Listener listener) {
			synchronized (this) {
				listeners.add(listener);
				
				switch(state) {
				case STATE_EXECUTING: {
					listener.started();
					break;
				}
				case STATE_PAUSED: {
					listener.stopped();
					break;
				}
				case STATE_FINISHED: {
					listener.finished();
					break;
				}
				}
			}
		}

		@Override
		public void removeLister(Listener listener) {
			synchronized (this) {
				listeners.remove(listener);
			}
		}
		
		public void setFinished() {
			synchronized (this) {
				state = STATE_FINISHED;
				
				for(Interruptable.Listener listener: listeners)
					listener.finished();
			}
		}
		
		public void setStopped() {
			synchronized (this) {
				state = STATE_PAUSED;
				
				for(Interruptable.Listener listener: listeners)
					listener.stopped();
			}
		}
		
		public void setResumed() {
			synchronized (this) {
				state = STATE_EXECUTING;
				
				for(Interruptable.Listener listener: listeners)
					listener.resumed();
			}
		}
		
		public void setStarted() {
			synchronized (this) {
				state = STATE_EXECUTING;
				
				for(Interruptable.Listener listener: listeners)
					listener.started();
			}
		}
		
		@Override
		public void start() {
			int stateLocal;
			synchronized(this) {
				stateLocal = state;
			}
			
			if(stateLocal != STATE_UNSTARTED)
				return;
			
			scheduler.executorService.execute(new Runnable() {
				@Override
				public void run() {
					setStarted();
					if(!taskNode.atEnd())
						scheduler.schedule(ScheduledTask.this, false);
					else
						setFinished();
				}
			});
		}

		@Override
		public void stop() {
			int stateLocal;
			synchronized(this) {
				stateLocal = state;
			}
			
			if(stateLocal != STATE_EXECUTING)
				return;
			
			synchronized (scheduler.tasksStopRequested) {
				scheduler.tasksStopRequested.add(this);
			}
		}

		@Override
		public void resume() {
			int stateLocal;
			synchronized(this) {
				stateLocal = state;
			}
			
			if(stateLocal != STATE_PAUSED)
				return;
			
			scheduler.executorService.execute(new Runnable() {
				@Override
				public void run() {
					setResumed();
					scheduler.schedule(ScheduledTask.this, false);
				}
			});
		}

		@Override
		public void finish() {
			int stateLocal;
			synchronized(this) {
				stateLocal = state;
			}
			
			switch(stateLocal) {
			case STATE_EXECUTING:
				synchronized (scheduler.tasksFinishRequested) {
					scheduler.tasksFinishRequested.add(this);
				}
				break;
			case STATE_PAUSED:
				synchronized (scheduler.tasksStopped) {
					scheduler.tasksFinishRequested.add(this);
				}
				resume();
//				setFinished();
				break;
			}
		}
		
		public ArrayList<SyncPoint> pendingBeforeSyncPoints = new ArrayList<SyncPoint>();

		@Override
		public void doBefore(SyncPoint syncPoint) {
			synchronized(pendingBeforeSyncPoints) {
				pendingBeforeSyncPoints.add(syncPoint);
			}
		}
	}
	
	private java.util.concurrent.ExecutorService executorService;
	private HashSet<Scheduler.ScheduledTask> tasksStopRequested = new HashSet<Scheduler.ScheduledTask>();
	private HashSet<Scheduler.ScheduledTask> tasksStopped = new HashSet<Scheduler.ScheduledTask>();
	private HashSet<Scheduler.ScheduledTask> tasksFinishRequested = new HashSet<Scheduler.ScheduledTask>();
//	private Hashtable<Scheduler.ScheduledTask, ArrayList<SyncPoint>> tasksSyncPoints = new Hashtable<Scheduler.ScheduledTask, ArrayList<SyncPoint>>();
	
	public Scheduler() {
		executorService = java.util.concurrent.Executors.newFixedThreadPool(1);
	}
	
	public Interruptable schedule(TaskNode task) {
		return new ScheduledTask(this, task);
		
//		executorService.execute(new Runnable() {
//			@Override
//			public void run() {
//				scheduledTask.setStarted();
//				if(!scheduledTask.taskNode.atEnd())
//					schedule(scheduledTask, false);
//				else
//					scheduledTask.setFinished();
//			}
//		});
		
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				scheduledTask.setStarted();
//				if(!scheduledTask.taskNode.atEnd())
//					schedule(scheduledTask, false);
//				else
//					scheduledTask.setFinished();
//			}
//		});
		
//		return scheduledTask;
	}
	
	private void handleSyncPoint(final ScheduledTask scheduledTask, final int i, final SyncPoint[] syncPoints) {
		if(i == syncPoints.length)
			schedule(scheduledTask, false);
		else {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					syncPoints[0].doAndThenCall(new Runnable() {
						@Override
						public void run() {
							handleSyncPoint(scheduledTask, i + 1, syncPoints);
						}
					});
				}
			});
		}
	}
	
	private void schedule(final ScheduledTask scheduledTask, final boolean finishRequested) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				synchronized (scheduledTask.pendingBeforeSyncPoints) {
					if(scheduledTask.pendingBeforeSyncPoints.size() > 0) {
						SyncPoint[] syncPoints = new SyncPoint[scheduledTask.pendingBeforeSyncPoints.size()];
						scheduledTask.pendingBeforeSyncPoints.toArray(syncPoints);
						scheduledTask.pendingBeforeSyncPoints.clear();
						handleSyncPoint(scheduledTask, 0, syncPoints);
						return;
					}
				}
				
				TaskNode nextNode;
				
				synchronized (scheduledTask.taskNode) {
					nextNode = scheduledTask.taskNode.next(finishRequested);
				}
				
				scheduledTask.taskNode = nextNode;
				
				if(scheduledTask.taskNode.atEnd()) {
					scheduledTask.setFinished();
				} else {
					boolean stopTask = false;
					
					synchronized (tasksStopRequested) {
						if(tasksStopRequested.contains(scheduledTask)) {
							tasksStopRequested.remove(scheduledTask);
							stopTask = true;
						}
					}
					
					if(stopTask) {
						synchronized (tasksStopped) {
							tasksStopped.add(scheduledTask);
						}
						scheduledTask.setStopped();
						return;
					}

					boolean finishTask = false;
					if(!finishRequested) {
						synchronized (tasksFinishRequested) {
							if(tasksFinishRequested.contains(scheduledTask)) {
								tasksFinishRequested.remove(scheduledTask);
								finishTask = true;
							}
						}
					} else {
						finishTask = true;
					}
					
					schedule(scheduledTask, finishTask);
				}
			}
		};
		
		executorService.execute(runnable);
		
//		if(1 == 1 || EventQueue.isDispatchThread()) {
//			SwingUtilities.invokeLater(runnable);
//		} else {
//			executorService.execute(runnable);
//		}
	}
}
