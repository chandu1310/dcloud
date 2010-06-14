package com.dcloud;

import com.dcloud.interfaces.DCloudTaskInt;

public abstract class DCloudTask implements DCloudTaskInt{
	
	private int taskstatus = OPEN;
	
	@Override
	public int status() {
		// TODO Auto-generated method stub
		return this.taskstatus;
	}
	
	@Override
	public DCloudResponse doTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DCloudResponse doTask(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public final void setStatus(int newstatus)
	{
		if(	newstatus==OPEN || 
			newstatus == SUBMITTED ||
			newstatus == INPROGRESS ||
			newstatus == COMPLETED ||
			newstatus == ABORTED
			)
		{
			this.taskstatus = newstatus;
		}
	}
}
