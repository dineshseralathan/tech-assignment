package com.ds.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.springboot.domain.DeadLockObject;
import com.ds.springboot.service.DeadLockInitiator;

/**
 * <code>RestController</code> that serves HTTP request methods
 * for this resource.
 * 
 * @author dinesh
 *
 */
@Controller
public class DeadLockController {
	
	@Autowired
	private DeadLockInitiator deadLockInitiator;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/deadlock")
	@ResponseBody
	public DeadLockObject createDeadLock(@RequestBody  List<DeadLockObject> objList)
	{   
		return deadLockInitiator.initiateDeadLock(objList);
			
		
	}

}
