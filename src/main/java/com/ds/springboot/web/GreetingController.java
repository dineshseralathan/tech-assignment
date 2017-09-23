package com.ds.springboot.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A <code>RestController</code> to handle 
 * simple greetings GET request that responds with
 * "Hello World".
 * 
 * @author dinesh
 *
 */
@RestController
@EnableAutoConfiguration
public class GreetingController {
	
	@RequestMapping(value="/greetings",produces = { "application/json", "application/text"})
	public String greet()
	{
		return "Hello World";
	}

}
