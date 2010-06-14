package com.dcloud;

import java.util.Hashtable;

import com.dcloud.interfaces.DCloudAppModuleInt;
import com.dcloud.interfaces.DCloudTaskInt;

public abstract class DCloudAppModule implements DCloudAppModuleInt{
	
	private final Hashtable<String, DCloudTaskInt> tasks;
	
	/*
	 * - This class will have collection of resources and tasks.
	 * - Method to invoke a task both synchronously and asynch.
	 */
	
	public DCloudAppModule() {
		// Register the listener for this app.
		tasks = new Hashtable<String, DCloudTaskInt>();
	}
	
	@Override
	public final void initializeModule(Object... o) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public final void addDCloudTask(String taskname, DCloudTaskInt task) {
		// TODO Auto-generated method stub
		tasks.put(taskname, task);
		
	}	
	
	@Override
	public final DCloudResponse invokeDCloudTask(String taskname) {
		// TODO Auto-generated method stub
		DCloudTaskInt task =  tasks.get(taskname);		
		DCloudResponse resp = task.doTask();
		return resp;
	}
	
	@Override
	public final DCloudResponse invokeDCloudTask(String taskname, Object... o) {
		// TODO Auto-generated method stub
		DCloudTaskInt task =  tasks.get(taskname);		
		DCloudResponse resp = task.doTask(o);
		return resp;
	}
}
