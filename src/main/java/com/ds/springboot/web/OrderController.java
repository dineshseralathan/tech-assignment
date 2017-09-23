package com.ds.springboot.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ds.springboot.domain.Order;
import com.ds.springboot.service.OrderService;


/**
 *<code>OrderController</code> is the Rest Controller class
 *responsible for handling all the HTTP request methods for
 * <code>Order</code> resource.
 * 
 * @author dinesh
 *
 */
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
   
	/**
	 * 
	 * @return all orders from Order table
	 * Note: usually this method is paginated
	 */
	@RequestMapping("/orders")
	public List<Order>  queryOrders()
	{  
		return orderService.getAllOrders();
		
	}
	
	/**
	 * 
	 * @param id, order id
	 * @return Order for give order id
	 */
	@RequestMapping("/orders/{id}")
	public Order queryOrderById(@PathVariable long id)
	{
		return orderService.getOrderById(id);
		
	}
	
	/**
	 * 
	 * @param status, status of the order to query
	 * @param quantity, number of order items to query
	 * @return list of orders satisfying query criteria
	 */
	@RequestMapping("/orders/query")
	public List<Order> queryOrderByStatusAndQty(@RequestParam(required = false) String status,
							@RequestParam(value="quantity",defaultValue = "0") Integer quantity)
	{   
		return orderService.getOrderByStatusAndQuantity(status,quantity);

		
	}
	
	/**
	 * Adds new Order to Order table
	 * @param order to be added
	 */
	@RequestMapping(method=RequestMethod.POST,value="/orders")
	@ResponseStatus(HttpStatus.CREATED)
	public Order addOrder(@RequestBody Order order)
	{   
		return orderService.addOrder(order);
	}
	

	 /**
	  * Deletes an Order for the given order id 
	  * @param id, id of the Order to be deleted
	  */
	@RequestMapping(method=RequestMethod.DELETE,value="/orders/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder( @PathVariable long id)
	{
		orderService.deleteOrder(id);
	}
	
	 /**
	  * Deletes all Orders from the repository
	  *
	  */
	@RequestMapping(method=RequestMethod.DELETE,value="/orders")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllOrder()
	{
		orderService.deleteAllOrder();
	}
	
}
