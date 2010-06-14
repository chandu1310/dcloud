package com.dcloud;

import java.io.Serializable;

import com.dcloud.interfaces.DCloudTaskInt;


public class DCloudRequest implements Serializable{

	private DCloudTaskInt task;
	private Object[] params;
	
	public DCloudRequest(DCloudTaskInt task, Object[] params) {
		// TODO Auto-generated constructor stub
		this.task = task;
		this.params = params;
	}

	public DCloudTaskInt getTask() {
		return task;
	}

	public void setTask(DCloudTaskInt task) {
		this.task = task;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
	
	
}
