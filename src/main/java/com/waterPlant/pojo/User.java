package com.waterPlant.pojo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(unique=true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String mobile;
	
	@Column( nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	@Column( nullable = false)
	private String address;

	private boolean enable;
	
	
	//private long orderId;

	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user") //cascade used since once user saved related info contact saves auto
	private List<Orders> orders=new ArrayList<>();
	
	public User() {
		super();
		
	}

	public User(int userId, String userName, String email, String mobile, String password, String role, String address,
			boolean enable) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
		this.address = address;
		this.enable = enable;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + ", role=" + role + ", address=" + address + ", enable=" + enable
				  + "]";
	}




	
	
	

}