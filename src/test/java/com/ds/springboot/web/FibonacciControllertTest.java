package com.ds.springboot.web;

 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ds.springboot.service.FibonacciService;

import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc 
public class FibonacciControllertTest { 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mocked
	FibonacciService fibonacciService; 
	
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}

	    @Test
	    public void testGETMethodForStatus() throws Exception {
	    	
	        new Expectations()
	        {
	            {      		
	            	fibonacciService.fibonacciSeries(10, true);
	                result = new long[]{1,1,2,3,5,8,13,21,34,55};

	            }
	        };
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fibonacci/10").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			assertEquals(HttpStatus.OK.value(),  result.getResponse().getStatus());
		

	    }
	    
	    @Test
	    public void testGETMethodContentTypeCompatibleWithJSON() throws Exception {
	    	
	        this.mockMvc.perform(get("/fibonacci/10").accept(MediaType.APPLICATION_JSON))
	                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	    }
	    
	    @Test
	    public void testForAPIResponse() throws Exception {
	     
	    	String expected ="[1,1,2,3,5,8,13,21,34,55]";
	    
		    new Expectations()
	        {
	            {      		
	            	fibonacciService.fibonacciSeries(10, true);
	                result = new long[]{1,1,2,3,5,8,13,21,34,55};
	
	            }
	        };
	                
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fibonacci/10").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			  	
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), JSONCompareMode.STRICT_ORDER);

	    }
  
	


}
