package com.ds.springboot.service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * <code>DeadLockMonitor</code> class detects dead lock
 * between two or more given threads using Java's<code>ThreadMXBean</code>
 * 
 * @author dinesh
 *
 */
@Service
public class DeadLockMonitor {
	
	private Boolean deadLockDetected = Boolean.FALSE;
	
	private static final Logger log = LoggerFactory.getLogger(DeadLockMonitor.class);
	
	  private long initialDelay =0;
	  private long period = 0;
	  private TimeUnit timeUnit;
	  
	  private ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
	  
	  private ScheduledExecutorService schedulerService =   Executors.newScheduledThreadPool(1);
	  
 
	  public DeadLockMonitor()  {
		  
	  }
 	  /**
 	   * Convenience Constructor
 	   * @param initialDelay
 	   * @param period
 	   * @param timeUnit
 	   */
	  public DeadLockMonitor(long initialDelay,long period,TimeUnit timeUnit) {
	   
		    this.initialDelay = initialDelay;
		    this.period = period;
		    this.timeUnit = timeUnit;
	  }
	  

		  final Runnable deadLockRunner = new Runnable() {
			    @Override
			    public void run() {
			    	
			      long[] deadlockedThreadIds = mxBean.findMonitorDeadlockedThreads();
			    
				      if (deadlockedThreadIds != null) {
				    	  
					        ThreadInfo[] threadInfo = mxBean.getThreadInfo(deadlockedThreadIds);
					      
					        reportDeadLock(threadInfo);
					      
				      }
			    }
			  };
	  

	  /**
	   * This method reports dead lock if threadInfo is found not null/empty.
	   * For the sake of reporting, additional logging is ignored. Also this method
	   * uses <code>Thread.currentThread.join()</code> method to join with current thread
	   * to avoid dead lock. There are various other ways to achieve it though.
	   * 
	   * @param deadLockedThreadInfo
	   */
	  protected void reportDeadLock(final ThreadInfo[] deadLockedThreadInfo)
		{
			  if (deadLockedThreadInfo != null) {
				  
				  deadLockDetected = Boolean.TRUE;

				  log.error("*************Deadlock detected!!!!!!!!!!*************");
				  
				  // join the current thread so its comes out of dead lock
				    try {
						Thread.currentThread().join();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				
			  }
			  else
			  {     
				   log.info("*************No Deadlock detected*************");
				
			  }
			  
			
					
		}
	  
	    
	  /**
	   * Kicks off scheduler service which executes the runnable with initial delay and subsequently
	   * with given period.
	   */
	  protected void start() {
		  
	    this.schedulerService.scheduleAtFixedRate(this.deadLockRunner,this.initialDelay, this.period, this.timeUnit);
    
	  }
   
	  /**
	   * 
	   * @return true if dead lock occurred, false otherwise
	   */
	protected Boolean isDeadLockDetected() {
		return deadLockDetected;
	}

}

