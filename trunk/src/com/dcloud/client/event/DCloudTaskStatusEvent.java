package com.dcloud.client.event;

import com.dcloud.common.DCloudTask;

public class DCloudTaskStatusEvent{
	private String taskName;
	private int taskStatus;
	private String eventMessage;
	private DCloudTask task;
	
	public DCloudTaskStatusEvent(String taskName, DCloudTask p_task, int p_taskStatus, String p_eventMessage) {
		// TODO Auto-generated constructor stub
		this.taskName = taskName;
		this.task = p_task;
		this.taskStatus = p_taskStatus;
		this.eventMessage = p_eventMessage;
	}	
	
	public String getTaskName() {
		return taskName;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public DCloudTask getSource()
	{
		return this.task;
	}
}
