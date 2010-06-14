package com.dcloud.interfaces;

import java.io.Serializable;

import com.dcloud.DCloudResponse;


public interface DCloudAppModuleInt extends Serializable{
	public void initializeModule(Object...o);
	public void addDCloudTask(String taskname, DCloudTaskInt task);
	public DCloudResponse invokeDCloudTask(String taskname);
	public DCloudResponse invokeDCloudTask(String taskname, Object...o);
}
