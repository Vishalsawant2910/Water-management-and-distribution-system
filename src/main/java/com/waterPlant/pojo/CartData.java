package com.waterPlant.pojo;

import java.util.ArrayList;
import java.util.List;

public class CartData {

	public static List<Product> cart;
	public static List <Cart> qt;
	
	static {
		cart=new ArrayList<Product>();
		qt=new ArrayList<Cart>();
	}
	
}
