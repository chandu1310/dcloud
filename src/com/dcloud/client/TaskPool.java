package com.dcloud.client;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.SwingWorker;

import com.dcloud.client.event.DCloudTaskStatusAdapter;
import com.dcloud.common.DCloudRequest;
import com.dcloud.common.DCloudTask;

import static com.dcloud.common.DCloudConstants.*;


public class TaskPool extends SwingWorker<TaskPool, Void>{
	private static Logger logger = Logger.getLogger(TaskPool.class.getName());
	private Hashtable<String, TaskPoolEntry> pool;	
	
	public TaskPool() {
		pool = new Hashtable<String, TaskPoolEntry>();
	}
	
	
	public void addSampleTask(String taskname, String cloudServiceURL) throws ClassNotFoundException, IOException
	{
		DCloudTask task = new DCloudTask("test.client.MyTestTaskForCloud", "execute", new Object[]{});
//		MyTestTaskForCloud task = new MyTestTaskForCloud();
		
		addTask(taskname
				, task 
				,cloudServiceURL);
	}
	
	public void addTask(String taskname, DCloudTask task, String cloudServiceURL)
	{
		TaskPoolEntry poolEntry = new TaskPoolEntry(taskname, cloudServiceURL, task, new DCloudTaskStatusAdapter() );
		pool.put(taskname, poolEntry);
	}
	
	public Integer getTaskStatus(String taskname)
	{
		Object o = pool.get(taskname);
		if(o!=null && o instanceof TaskPoolEntry)
		{
			TaskPoolEntry poolEntry = (TaskPoolEntry)o;
			return new Integer(poolEntry.getStatus());
		}
		return null;
	}
	
	public DCloudTask getTaskForConsumption(String taskname) throws Exception
	{
		TaskPoolEntry poolEntry;
		Object o = pool.get(taskname);
		
		if(o!=null && o instanceof TaskPoolEntry)
		{
			poolEntry = (TaskPoolEntry)o;
			if(poolEntry.getStatus() == EXEC_DONE || poolEntry.getStatus() == FAILED)
			{
				// remove and return the task object from pool.
				return pool.remove(taskname).getTask();
			}
			else
				logger.info("Execution status: "+ poolEntry.getStatus());
		}
		throw new Exception("Task not found in pool or is not yet completed.");
	}
	
	public void executeTaskPool() throws Exception
	{
		execute();
	}


	@Override
	protected TaskPool doInBackground() throws Exception {
		// pic each task and post it to the url and gather responses to set status.	
		Enumeration<String> keys= pool.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			TaskPoolEntry poolEntry = (TaskPoolEntry)pool.get(key);
//			DCloudTask task = poolEntry.getTask();
				
			try{
				if(poolEntry.getStatus() == SUB_PENDING)
				{
					logger.info("TaskPool: Submitting task: "+poolEntry.getName());
					poolEntry.execute();
				}
			}catch (Exception e) {				
				logger.info("TaskPool:executeTaskPool - Failed to execute the task: "+key);
				e.printStackTrace();
			}
		}
		
		keys = null;
		Set<String> keyset = pool.keySet();
		logger.info("Total number of tasks posted in pool: "+keyset.size());
		Iterator<String> keyset_itr = keyset.iterator();
		while(keyset_itr.hasNext())
		{
			String key = keyset_itr.next();
			TaskPoolEntry poolEntry = (TaskPoolEntry)pool.get(key);
			try{
				if(poolEntry.getStatus() == SUBMITTED)
				{
					logger.info("TaskPool: Waiting pool thread for : "+ poolEntry.getName());
					poolEntry.join();
				}
			}catch (Exception e) {				
				logger.info("TaskPool:executeTaskPool - Failed to execute the task: "+key);
				e.printStackTrace();
			}
		}
		logger.info("TaskPool: Done with pool execution. All task threads completed.");
		return this;
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
	}
}
