package com.dcloud.client;


import java.io.ObjectInputStream;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.dcloud.common.DCloudTask;



public class HttpClientForCloud
{	
	private DCloudTask req;
	private String cloudServiceURL;
	private Logger logger = Logger.getLogger(HttpClientForCloud.class.getName());
	
	public HttpClientForCloud(DCloudTask req, String cloudServiceURL) {
		this.req = req;
		this.cloudServiceURL = cloudServiceURL;
	}
	
	public void sendTaskForExecution() {
	   	try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(cloudServiceURL);
	        SerializableEntity reqEntity = new SerializableEntity(req, false);
	        reqEntity.setContentType("application/java-byte-code");
//	    	      reqEntity.setChunked(true);
	        httppost.setEntity(reqEntity);
	        
	        logger.info("SENDING TASK FOR EXECUTION: " + httppost.getRequestLine());
	        HttpResponse response = httpclient.execute(httppost);
	        
	        HttpEntity resEntity = response.getEntity();
	        //SerializableEntity resEntity = (SerializableEntity)response.getEntity();
	        logger.info("-------------------RESPONSE FROM SERVER---------------------");
	        logger.info("-------------------START---------------------");
	        logger.info( response.getStatusLine().toString() );
	        if (resEntity != null) {
	            logger.info("Response content length: " + resEntity.getContentLength());
	            logger.info("Chunked?: " + resEntity.isChunked());
	            
	            ObjectInputStream ois = new ObjectInputStream(resEntity.getContent());
	            Object res_object = ois.readObject();
	            if(res_object != null && res_object instanceof DCloudTask)
	            {
	            	logger.info("Stripped the response object from stream. Extracting task result and setting it in DCloudTask object");
	            	DCloudTask taskResp = (DCloudTask)res_object;
	           		req.setTask_function_return_value( taskResp.getTask_function_return_value() );
	            	req.setTask_result(taskResp.getTask_result());	
	            }
	        }
	        logger.info("-------------------END---------------------");        
	        httpclient.getConnectionManager().shutdown();  
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
	}
 }

