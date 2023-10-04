package com.waterPlant.pojo;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "order_id")
	 private int id;
	
	private int user_id;
//	private int product_id;
	 private double total;
//	 private int quantity;
	private String shippingAddress;
	
	
	
	private String shippingDate;
	
	@ManyToOne
	@JsonIgnore
	private User user;

	public Orders(int id, int user_id, int product_id, double total, int quantity, String shippingAddress,
			String shippingDate, User user) {
		super();
		this.id = id;
		this.user_id = user_id;
//		this.product_id = product_id;
		this.total = total;
//		this.quantity = quantity;
		this.shippingAddress = shippingAddress;
		this.shippingDate = shippingDate;
		this.user = user;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

//	public int getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(int product_id) {
//		this.product_id = product_id;
//	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		return "Orders [id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", total=" + total
//				+ ", quantity=" + quantity + ", shippingAddress=" + shippingAddress + ", shippingDate=" + shippingDate
//				+ ", user=" + user + "]";
//	}
	
	
	
	
	
	
}
