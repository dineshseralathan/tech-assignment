package com.ds.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ds.springboot.domain.Order;
import com.ds.springboot.domain.OrderRepository;

/**
 * This <code>TestRestApiApplication</code> class is responsible for bootstrapping 
 * Spring Web Application as a stand-alone application performing all necessary 
 * steps that a servlet-container will do. It does the following steps behind the
 * scene.
 * 
 * <ul>
 *       <li>Sets up Default Configuration</li>
 *       <li>Starts Spring Application Context</li>
 *       <li>Performs Class Path Scan</li>
 *       <li>Starts Tomcat Server</li>
 * </ul>
 * 
 * @author dinesh
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ds.springboot")
public class TestRestApiApplication implements CommandLineRunner {
   
	
	private static final Logger log = LoggerFactory.getLogger(TestRestApiApplication.class);
	
	@Autowired
	OrderRepository repository;
	
	/**
	 * Main method that bootstraps the application by calling
	 * <code>SpringApplication</<code>'s <code>run</code> method.
	 * 
	 * 	@param args
	 */
	public static void main(String[] args) {
		
        SpringApplication.run(TestRestApiApplication.class, args);
	}
	
	
	
	/**
	 * Load <code>Order</code> table from embedded HSQL DB with initial data using
	 * Spring JPA - OrderRepository before applications start up.
	 *
	 */
	@Override
    public void run(String...args) throws Exception {
       
    	
	    	//Load few Orders at the server start up for initial querying 
			repository.save(new Order("CISLRT", 4,  "Cisco Router",     "Open"));
			repository.save(new Order("SMGLRT", 3,  "Samsung Router",   "Canceled"));
			repository.save(new Order("LGLRT",  1,  "LG Router",        "In-Transit"));
			repository.save(new Order("MTLRT",  2,  "Motorola Router",  "Delivered"));

	        if(repository.findAll().iterator().hasNext()) {
	     	
		    	 log.info("*******************************************************************");
		    	 log.info("*****Order Table created and loaded successfully into database*****");
		    	 log.info("*******************************************************************");
	       }
    }
	
	

}
