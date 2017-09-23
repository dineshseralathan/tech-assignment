package com.ds.springboot.web;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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

import com.ds.springboot.domain.Order;
import com.ds.springboot.service.OrderService;

import mockit.Expectations;
import mockit.Mocked;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc 
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mocked
	OrderService orderService; 

	

	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
    	
	@Test
	public void testQueryOrderById() throws Exception {
		 
		String expected = "{\"id\":1,\"productId\":\"CISLRT\",\"quantity\":4,\"description\":\"Cisco Router\",\"status\":\"Open\"}";
			 
		  new Expectations()
	        {
	            {      		
	            	orderService.getOrderById(1L);
	                result = new Order(1l,"CISLRT", 4,"Cisco Router", "Open");

	            }
	        };

		// Using Mock svc builder provided by SpringBoot to call rest end point without having to start the server
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		  	
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), JSONCompareMode.STRICT_ORDER);
	}
	
	
	@Test
	public void testQueryOrderByStatusAndQty() throws Exception {
		 
		String expectedOutput = "[{\"id\":1,\"productId\":\"CISLRT\",\"quantity\":4,\"description\":\"Cisco Router\",\"status\":\"Open\"},"
				               +"{\"id\":2,\"productId\":\"MTLRT\",\"quantity\":3,\"description\":\"Motorola Router\",\"status\":\"Open\"}]";
		  new Expectations()
	        {
	            {  
	            	orderService.getOrderByStatusAndQuantity("Open",2);
        	        result = Arrays.asList(new Order[] {new Order(1l,"CISLRT", 4,"Cisco Router", "Open"),
        	        							new Order(2l,"MTLRT", 3,"Motorola Router", "Open")});
        	       


	            }
	        };

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/query?status=Open&quantity=2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    JSONAssert.assertEquals(expectedOutput, result.getResponse().getContentAsString(), JSONCompareMode.STRICT_ORDER);
		
	}
	
	
	@Test
	public void testQueryOrderByStatus() throws Exception {
		 
		String expectedOutput = "[{\"id\":5,\"productId\":\"PSLRT\",\"quantity\":1,\"description\":\"Panasonic Router\",\"status\":\"In-Transit\"}]";
	
		   new Expectations()
	        {
	            {  
	            	orderService.getOrderByStatusAndQuantity("In-Transit",0);
        	        result = new Order(5l,"PSLRT", 1,"Panasonic Router", "In-Transit");
        	       

	            }
	        };

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/query?status=In-Transit").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(expectedOutput, result.getResponse().getContentAsString(), false);

	}
	
	
	@Test
	public void testQueryOrderByQty() throws Exception {
		 
		String expectedOutput = "[{\"id\":3,\"productId\":\"MTLRT\",\"quantity\":4,\"description\":\"Motorola Router\",\"status\":\"Delivered\"}]";
		
		  new Expectations()
	        {
	            {  
	            	orderService.getOrderByStatusAndQuantity(null,4);
        	        result = new Order(3l,"MTLRT",4,"Motorola Router","Delivered");
    

	            }
	        };

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/query?quantity=4").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	JSONAssert.assertEquals(expectedOutput, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void testAddOrder() throws Exception {
		
		Order mockedOrder = new Order(1l,"SMGLRT", 2,"Samsung Router", "Closed"); 
		 
		  new Expectations()
	        {
	            {  
	            	orderService.addOrder(mockedOrder);
	            	result = mockedOrder;

	            }
	        };
	        
	   String newOrder = "{\"id\":1,\"productId\":\"SMGLRT\",\"quantity\":2,\"description\":\"Samsung Router\",\"status\":\"Closed\"}";
		
		
	   RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/orders").accept(MediaType.APPLICATION_JSON).content(newOrder)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.CREATED.value(),  result.getResponse().getStatus());
		
		assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE,  result.getResponse().getContentType());
		

	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		
	
		  new Expectations()
	        {
	            {  
	            	orderService.deleteOrder(1l);
	            	result = null;
	  	        }
	        };
	        
	   String order = "{\"id\":1,\"productId\":\"SMGLRT\",\"quantity\":2,\"description\":\"Samsung Router\",\"status\":\"Closed\"}";
		
		
	   RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/orders/1").accept(MediaType.APPLICATION_JSON).content(order)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
		
		assertTrue(result.getResponse().getContentAsString().equals(""));		

	}


}
