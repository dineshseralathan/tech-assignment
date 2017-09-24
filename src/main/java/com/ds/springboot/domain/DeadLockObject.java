/**
 * 
 */
package com.ds.springboot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * <code>Data Transfer Object</code> that mimics 
 * <code>Object</code>'s <i>instance/i> on which
 * threads acquires lock to create a deadlock.
 * 
 * @author dinesh
 *
 */

public class DeadLockObject {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String name;
	
	private boolean deadlockDetected; 
	
	public DeadLockObject() {
		
	}
	
		
	public DeadLockObject(String name)
	{
		this.name = name;
	}
	
	public DeadLockObject(boolean deadlockDetected)
	{
		this.deadlockDetected = deadlockDetected;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isDeadlockDetected() {
		return deadlockDetected;
	}


	public void setDeadlockDetected(boolean deadlockDetected) {
		this.deadlockDetected = deadlockDetected;
	}
	


	@Override
	public String toString()
	{ 
		return name;
	}


}
