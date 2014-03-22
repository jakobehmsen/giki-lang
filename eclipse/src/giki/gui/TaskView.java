package giki.gui;

import giki.runtime.Output;

public class TaskView {
	private int taskNo;
	private TaskListView taskListView;
	
	public TaskView(int taskNo, TaskListView taskListView) {
		this.taskNo = taskNo;
		this.taskListView = taskListView;
	}

	public void setStatus(String status) {
		taskListView.setTaskStatus(taskNo, status);
	}

	public void setOutput(Output output) {
		taskListView.setTaskOutput(taskNo, output);
	}

	public void setResult(String result) {
		taskListView.setTaskResult(taskNo, result);
	}

	public void end() {
		taskListView.endTask(taskNo);
	}
}
