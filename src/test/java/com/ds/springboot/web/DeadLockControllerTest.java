//package com.ds.springboot.web;
//
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//
//import com.ds.springboot.domain.DeadLockObject;
//import com.ds.springboot.service.DeadLockInitiator;
//
//import mockit.Expectations;
//import mockit.Mocked;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DeadLockControllerTest {
// 
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Mocked
//	private DeadLockInitiator initiator;
//	
//	
//	@Before
//	public void init() {
//	    MockitoAnnotations.initMocks(this);
//	}
//	
//    @Test
//    public void testPOSTMethodForStatus() throws Exception {
//    	
//    	List<DeadLockObject> mockedObjList = new ArrayList<DeadLockObject>();
//    	
//    	DeadLockObject d1 = new DeadLockObject("Object 1");
//    	DeadLockObject d2 = new DeadLockObject("Object 2");
//    	mockedObjList.add(d1);
//    	mockedObjList.add(d2);
//		
//    	 
//        new Expectations()
//        {
//            {      		
//            	initiator.initiateDeadLock(mockedObjList);
//            	result= new DeadLockObject(true);
//            	
//            }
//        };
//        
//        String inputObjListAsString = "[{\"name\":\"Object 1\"},{\"name\":\"Object 2\"}]";
//      
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/deadlock").
//					        		accept(MediaType.APPLICATION_JSON).
//					        		content(inputObjListAsString).contentType(MediaType.APPLICATION_JSON);
//        
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		
//	//	System.err.println("testPOSTMethodForStatus*** "+result.getResponse().getContentAsString());
//		
//	//	assertEquals(HttpStatus.OK.value(),  result.getResponse().getStatus());
//	
//
//    }
//    
//
//}
