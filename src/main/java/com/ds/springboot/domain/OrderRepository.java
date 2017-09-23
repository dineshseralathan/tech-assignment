/**
 * 
 */
package com.ds.springboot.domain;

import java.util.List;


import org.springframework.data.repository.PagingAndSortingRepository;


/** 
 * <code>OrderRepository</code> interface extends <code>CrudRepository<Order,Long></code>
 * there by inheriting default CRUD methods provided by Spring JPA. Spring JPA takes care
 * of connecting to embedded in process HSQL db (by maven runtime dependency) there by 
 * alleviating developers to create JDBC connection and writing SQL queries.
 * 
 * It also has custom CRUD methods by extending query creation by method names.
 * 
 * @author dinesh
 *
 */

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	List<Order> findByStatus(String status);
	
	List<Order> findByQuantity(int quantity);

	List<Order> findByStatusAndQuantity(String status, int quantity);

}
