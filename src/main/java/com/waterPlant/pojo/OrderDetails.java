package com.waterPlant.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "OrderDetails_id")
	 private int id;
	
	@ManyToOne
//	@JoinColumn(name="order_id")
	private Orders order;
	
	@ManyToOne
//	@JoinColumn(name="product_id")
	private Product product;
	
	 private int quantity;
		
		
	 private double amount;
	 
	 private int order_id;
	 private int product__id;
	 
	 
	 


	


	public int getOrder_id() {
		return order_id;
	}


	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	public int getProduct__id() {
		return product__id;
	}


	public void setProduct__id(int product__id) {
		this.product__id = product__id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}


	public Product getProduct() {
		return product;
	}
	


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
}
