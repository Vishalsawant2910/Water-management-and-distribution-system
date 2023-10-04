package com.waterPlant.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Cart")
public class Cart {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cart_id")
	private long id;
	 
	

	 @ManyToOne
		@JsonIgnore
		private Product product;
	 
	 @OneToOne
	 @JsonIgnore
	 private User user;

	
	
	
	
	 private int quantity;
	
	
	 private double amount;

//	 @OneToOne
//		@JsonIgnore
//		private User user;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(long id, long pid, int uid, int quantity, double amount) {
		super();
		this.id = id;
		
		
		this.quantity = quantity;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
