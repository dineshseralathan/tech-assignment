package com.ds.springboot.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ds.springboot.domain.DeadLockObject;

/**
 * <code>DeadLockInitiator</code> is responsible for creating threads(in this
 * case 2 threads) and enforcing dead lock on objects passed as method arguments
 * to <code>DeadLockInitiator</code>'s <code>initiateDeadLock</code> method.
 * <code>DeadLockMonitor</code> monitors the threads and detects the dead lock
 * after sleeping for stipulated time ( to give just about enough time to let
 * dead lock happen).
 * 
 * @author dinesh
 *
 */
@Service
public class DeadLockInitiator {
	
	private static final Logger log = LoggerFactory.getLogger(DeadLockInitiator.class);
	
	/**
	 * This method is responsible for creating dead lock and monitoring the same
	 * by <code>DeadLockMonitor</code>
	 * @param objList
	 * @return DeadLockObject which has a boolean indicating dead lock
	 * occurred or not.
	 */
	public DeadLockObject initiateDeadLock(List<DeadLockObject> objList)
	{   
		DeadLockObject res = new DeadLockObject();
		
     	DeadLockMonitor deadlockMonitor = new DeadLockMonitor(1,1,TimeUnit.SECONDS);
		
		deadlockMonitor.start();
				
		
		final  DeadLockObject obj1 = objList.get(0);
	
		final  DeadLockObject obj2 = objList.get(1);
	
		startThreads(obj1, obj2);

		// sleep for 1 second in order to wait for dead lock to happen
		// based on time period passed to DeadLockMonitor constructor
		   try {
		        TimeUnit.MILLISECONDS.sleep(2000);
		      } catch (InterruptedException ignore) {
		      }
	    // now check for dead lock
		res.setDeadlockDetected(deadlockMonitor.isDeadLockDetected());
	    return res;
	
	}
    /**
     * This methods creates 2 instances of threads and enforces dead lock on passed
     * <code>DeadLockObject</code> instances using <code>synchronized</code> blocks.
     * @param obj1
     * @param obj2
     */
	private void startThreads(final DeadLockObject obj1, final DeadLockObject obj2) {
		
		
		Thread thread1 = new Thread(new Runnable() {
		  @Override
		  public void run() {
			  
		    synchronized (obj1) {
		    	
		    	 log.info(Thread.currentThread().getName()+" acquired lock on "+obj1.toString());
		      try {
		        TimeUnit.MILLISECONDS.sleep(200);
		      } catch (InterruptedException ignore) {
		      }
		      synchronized (obj2) {
		    	  
		    	  log.info(Thread.currentThread().getName()+" acquired lock on "+obj2.toString());
		      }
		    }
		  }

		});
		thread1.setName("Thread A");
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {
		  @Override
		  public void run() {
		    
			  synchronized (obj2) {
				  
		        log.info(Thread.currentThread().getName()+" acquired lock on "+obj2.toString());
		      synchronized (obj1) {
		    	  
		    	  log.info(Thread.currentThread().getName()+" acquired lock on "+obj1.toString());
		      }
		    }
		  }
		});
		thread2.setName("Thread B");
		thread2.start();
	}
	

}
