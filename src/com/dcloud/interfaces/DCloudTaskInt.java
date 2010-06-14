package com.dcloud.interfaces;

import java.io.Serializable;

import com.dcloud.DCloudResponse;

public interface DCloudTaskInt extends Serializable{
	public static final int OPEN = 0;
	public static final int SUBMITTED = 1;
	public static final int INPROGRESS = 2;
	public static final int COMPLETED = 3;
	public static final int ABORTED = -3;
	
	public int status();
	public DCloudResponse doTask();
	public DCloudResponse doTask(Object...o);
}
