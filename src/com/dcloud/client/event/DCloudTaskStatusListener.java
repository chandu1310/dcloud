package com.dcloud.client.event;

public interface DCloudTaskStatusListener {
	public void taskPosted(DCloudTaskStatusEvent event);
	public void taskSubmitted(DCloudTaskStatusEvent event);
	public void taskSuccessful(DCloudTaskStatusEvent event);
	public void taskFailed(DCloudTaskStatusEvent event);
	public void targetUnavailable(DCloudTaskStatusEvent event);
}
