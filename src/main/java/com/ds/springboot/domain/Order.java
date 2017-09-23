package com.ds.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <code>Entity</code> class which represents <code>Order</code>(aka order_table).
 * 
 * @author dinesh
 *
 */

@Entity
@Table(name = "order_table") /* Order is reversed key word in SQL and hence the table name has to be different*/
public class Order {
	 
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String productId;
	private int quantity;
	private String description;
	private String status;
	
		
	/**
	 * default no-arg constructor for JPA
	 */
	public Order() {
		
	}
	/**
	 * @param productId
	 * @param count
	 * @param description
	 * @param status
	 */
	public Order(String productId, int quantity, String description, String status) {
		
		this.productId = productId;
		this.quantity = quantity;
		this.description = description;
		this.status = status;
	}
	
	/**
	 * @param id
	 * @param productId
	 * @param count
	 * @param description
	 * @param status
	 */
	public Order(Long id,String productId, int quantity, String description, String status) {
		
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.description = description;
		this.status = status;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/* 
	 * for readability and logging purpose
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", description="
				+ description + ", status=" + status + "]";
	}

	 /* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((productId == null) ? 0 : productId.hashCode());
			result = prime * result + quantity;
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			return result;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Order))
				return false;
			Order other = (Order) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (productId == null) {
				if (other.productId != null)
					return false;
			} else if (!productId.equals(other.productId))
				return false;
			if (quantity != other.quantity)
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			return true;
		}

}
