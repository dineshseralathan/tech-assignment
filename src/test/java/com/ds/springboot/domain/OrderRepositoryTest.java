/**
 * 
 */
package com.ds.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit4.SpringRunner;

import com.ds.springboot.domain.Order;
import com.ds.springboot.domain.OrderRepository;

/**
 * @author dines
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
 
	List<Order> dbList1 = new ArrayList<Order>();
	List<Order> dbList2 = new ArrayList<Order>();
	
	@Autowired
	private TestEntityManager testEntityManager;
	// to get the existing db entries and store in a list before
	// executing test cases
	//@Autowired OrderRepository repo;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Before
	public void setUp()
	{   
		// back up the existing entries 
		orderRepository.findAll().forEach(dbList1::add);
		 // delete the entries 
		 for(Order o: dbList1) {
			 orderRepository.delete(o.getId());
		 }
		 

	}
	
	@Test
	public void testFindByQuantity() throws Exception {
	
		testEntityManager.persist(new Order("CISLRT", 8,"Cisco Router", "Open"));
        List<Order> order = this.orderRepository.findByQuantity(8);
        System.err.println("testFindByQuantity::::"+order);
        assertThat("CISLRT".equals(order.get(0).getProductId()));
        assertThat("Open".equals(order.get(0).getProductId()));
        assertThat(8==order.get(0).getQuantity());
        
	}
	
	@Test
	public void testFindByOne() throws Exception {
	
		Order o = new Order("TSTRT", 5,"Tisco Router", "Open");
		
		testEntityManager.persist(o);
		long id = (long) testEntityManager.getId(o);
	    
        Order order = this.orderRepository.findOne(id);
        System.err.println("testFindByOne::::"+order);
        assertThat("CISLRT".equals(order.getProductId()));
        assertThat("Open".equals(order.getProductId()));
        assertThat(8==order.getQuantity());
        
	}
	
	@Test
	public void testFindByStatus() throws Exception {
		
	   			 
		testEntityManager.persist(new Order("CISLRT", 4,"Cisco Router", "In-Transit"));
		testEntityManager.persist(new Order("LGLRT", 6,"LG Router", "In-Transit"));
        List<Order> order = this.orderRepository.findByStatus("In-Transit");
        System.err.println("testFindByStatus:::"+order);
       
        assertThat("CISLRT".equals(order.get(0).getProductId()));
        assertThat("LGLRT".equals(order.get(1).getProductId()));
        
        
        assertThat("CISLRT".equals(order.get(0).getDescription()));
        assertThat("LGLRT".equals(order.get(1).getDescription()));
        
        assertThat(4==order.get(0).getQuantity());
        assertThat(6==order.get(1).getQuantity());
        
        assertThat(order.get(0).getStatus().equals(order.get(1).getStatus()));
	}
	
	@Test
	public void testFindByStatusAndQuantity() throws Exception {
	  
		testEntityManager.persist(new Order("CISLRT", 4,"Router A", "Open"));
		testEntityManager.persist(new Order("LGLRT", 6,"Router B", "Canceled"));
		testEntityManager.persist(new Order("CISLRT", 2,"Router Cr", "Open"));
		testEntityManager.persist(new Order("LGLRT", 6,"Router D", "In-Transit"));
		
		
		  List<Order> order = this.orderRepository.findByStatusAndQuantity("Open", 4);
		  
		  assertThat("CISLRT".equals(order.get(0).getProductId()));
	      assertThat("Router A".equals(order.get(0).getDescription()));
          assertThat(4==order.get(0).getQuantity());

		
	}
	
	
	@Test
	public void testSave() throws Exception {
		
        Order order = orderRepository.save(new Order("SNYLRT", 3,"Cisco Router", "Canceled"));
        System.err.println("testSave::::"+order);
        assertThat("SNYLRT".equals(order.getProductId()));
        assertThat("Canceled".equals(order.getStatus()));
        assertThat(3==order.getQuantity());
	}

	@Test
	public void testDelete() throws Exception {
	
		
		Order o = new Order("LNVRT", 1,"Lenevo Router", "Open");
		testEntityManager.persist(o);
				
		long id = (long) testEntityManager.getId(o);
		
         this.orderRepository.delete(id);
         
         Order order = this.orderRepository.findOne(id);
         System.err.println("testDelete:::"+order);
        	assertNull(order);
	}
	
	
	@After
	public void tearDown()
	{   
		// back up the existing entries 
		orderRepository.findAll().forEach(dbList2::add);
		 // delete the entries 
		 for(Order o: dbList2) {
			 orderRepository.delete(o.getId());
		 }
			
	   // insert records back for application testing using rest end point
		for(Order order: dbList1) {
			orderRepository.save(order);
		}
		    
	}
	
}
