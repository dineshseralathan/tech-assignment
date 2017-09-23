package com.ds.springboot.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.springboot.domain.Order;
import com.ds.springboot.domain.OrderRepository;


/**
 * <code>OrderService<code/> is service class that all the
 * necessary CRUD methods defined.It delegates the responsibility
 * to <code>OrderRepository</code> class.
 * 
 * @author dinesh
 *
 */
@Service
public class OrderService { 
	
	@Autowired  
	private OrderRepository orderRespository;

   /**
    * Returns all the order items from the repository
    * @return list of orders
    */
	public List<Order> getAllOrders() {
		
		List<Order> orderList = new ArrayList<Order>();
		
		 orderRespository.findAll().forEach(orderList::add);
		 
		 return orderList;

	}

   /**
    * Queries an Order based on the order id
    * @param id
    * @return
    */
	public Order getOrderById(long id) {
		
		 return orderRespository.findOne(id);
	}

   /**
    * Adds an Order item to repository
    * @param order
    * @return added Order
    */
	public Order addOrder(Order order) {
		
		 return orderRespository.save(order);
		
	}

    /**
     * Deletes an Order for the given order id
     * @param id
     */
	public void deleteOrder(long id) {
		
		orderRespository.delete(id);
		
	}

    /**
     * Returns list of orders matching status and/or quantity
     * depending on the query param passed
     * @param status
     * @param quantity
     * @return list of orders matching query criteria
     */
	public List<Order> getOrderByStatusAndQuantity(String status, int quantity) {
		
		if(status!=null && !status.isEmpty() && quantity>0)
		{
			return orderRespository.findByStatusAndQuantity(status,quantity);
		}
		else if(status!=null && !status.isEmpty())
		{
			return orderRespository.findByStatus(status);
		}
		else if(quantity>0)
		{
			return orderRespository.findByQuantity(quantity);
		}
		
		return null;
		
		
	}

   /**
    * Deletes all orders from embedded HQSL db
    */
	public void deleteAllOrder() {
		
		 orderRespository.deleteAll();
	}
}
