package com.dcloud.interfaces;

import java.io.Serializable;

public interface DCloudResponseInt extends Serializable{
	public static final int INVALID = 0;
	public static final int SUCCESS = 1;
	public static final int FAILED = 2;
	
	public int result();
	public Object response();
}
