package com.dcloud.client;
 
import java.util.logging.Logger;

import com.dcloud.client.event.DCloudTaskStatusListener;
import com.dcloud.common.DCloudTask;

import static com.dcloud.common.DCloudConstants.*;

public class TaskPoolEntry extends Thread{
	private String cloudServiceURL;
	private int status = SUB_PENDING;
	private DCloudTask task;	
	private DCloudTaskStatusListener listener;
	private static Logger logger = Logger.getLogger(TaskPoolEntry.class.getName());
	
	public TaskPoolEntry(String taskname, String cloudURL, DCloudTask task, DCloudTaskStatusListener listener )
	{
		super(taskname);
		this.setCloudServiceURL(cloudURL);
		this.task = task;
		this.listener = listener;
	}
	
	public int getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

	public DCloudTask getTask() {
		return task;
	}

	public void setTask(DCloudTask task) {
		this.task = task;
	}

	public int setStatus(int status) {
		// TODO Auto-generated method stub
		int i = this.status;
		this.status = status;
		return i;
	}
	
	public void setCloudServiceURL(String cloudServiceURL) {
		this.cloudServiceURL = cloudServiceURL;
	}

	public String getCloudServiceURL() {
		return cloudServiceURL;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		setStatus(SUBMITTED);
		logger.info(getName()+ ": Running http client for excution.");
		HttpClientForCloud cl = new HttpClientForCloud(task, cloudServiceURL);
		cl.sendTaskForExecution(); // Takes time for completion		
		setStatus(task.getTask_result());
		logger.info(getName()+ ": Done excution.");
	}
	
	public void execute()
	{
		start();
	}
}
