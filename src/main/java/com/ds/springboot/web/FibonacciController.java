package com.ds.springboot.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.springboot.service.FibonacciService;

@RestController
public class FibonacciController {
	
	@Autowired
	private FibonacciService fibService;
	
	@RequestMapping("/fibonacci/{number}")
	public List<Long> fibSeries(@PathVariable int number)
	{   		
		return fibService.fibonacciSeries(number,true);
		
	}

}
