package com.waterPlant.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "product_id")
	 private long id;
	
	@Column(name = "product_name",nullable = false)
	  private String productName;
	
	@Column(nullable = false)
	  private double price;
	
	@Column(nullable = false)
	 private int qtyInLtr;
	
	
	private String image_name;
	 private String description;
	 private int quantityInCart;
	 private double amountInCart;
	 
	
	
	 



	public double getAmountInCart() {
		return amountInCart*this.quantityInCart;
	}

	public void setAmountInCart(double amountInCart) {
		this.amountInCart = amountInCart;
	}

	public int getQuantityInCart() {
		return quantityInCart;
	}

	public void setQuantityInCart(int quantityInCart) {
		this.quantityInCart = quantityInCart;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	//constructor
	 public Product() {
		super();
		
	}

	//parameterized constructor
	 public Product(long id, String productName, double price, int qtyInLtr, String description) {
			super();
			this.id = id;
			this.productName = productName;
			this.price = price;
			this.qtyInLtr = qtyInLtr;
			this.description = description;
		}


	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public int getQtyInLtr() {
		return qtyInLtr;
	}

	public void setQtyInLtr(int qtyInLtr) {
		this.qtyInLtr = qtyInLtr;
	}

	@Override
	public String toString() {
		return "Products [productId=" + id + ", productName=" + productName + ", price=" + price + ", qtyInLtr="
				+ qtyInLtr + ", description=" + description + "]";
	}


	 
	
	
	  
}
