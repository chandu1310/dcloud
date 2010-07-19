package com.dcloud.client.event;

public class DCloudTaskStatusAdapter implements DCloudTaskStatusListener {

	@Override
	public void targetUnavailable(DCloudTaskStatusEvent event) {
		// TODO Auto-generated method stub
		System.err.println("Target is Unavailable");		
	}

	@Override
	public void taskFailed(DCloudTaskStatusEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskPosted(DCloudTaskStatusEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskSubmitted(DCloudTaskStatusEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskSuccessful(DCloudTaskStatusEvent event) {
		// TODO Auto-generated method stub
		
	}

}
