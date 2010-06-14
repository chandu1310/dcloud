package com.dcloud;

import com.dcloud.interfaces.DCloudResponseInt;

public final class DCloudResponse implements DCloudResponseInt
{	
	private Object responseObject;
	private int resultCode = INVALID;
	
	@Override
	public int result() {
		// TODO Auto-generated method stub
		return resultCode;
	}
	
	public void setResultCode(int rc)
	{
		this.resultCode = rc;
	}
	
	@Override
	public Object response() {
		// TODO Auto-generated method stub
		return this.responseObject;
	}
	
	public void setResponseObject(Object o)
	{
		this.responseObject = o;
	}
	
	public static DCloudResponse getDefaulCloudResponse()
	{
		return new DCloudResponse();
	}
}
