package com.dcloud.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DCloudUtil {
	public static byte[] objectToBytes(DCloudTask object)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
		}catch(java.io.IOException ioe){
			ioe.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	public static Object bytesToObject(byte[] bytes)
	{
		Object object = null;
		try{
		object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
		}catch(java.io.IOException ioe){
			ioe.printStackTrace();
		}catch(java.lang.ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		return object;	
	}
}
