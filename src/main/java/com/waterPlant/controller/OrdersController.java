package com.waterPlant.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waterPlant.services.OrderDeatilsServiceImpl;
import com.waterPlant.services.OrderServiceImpl;

@Controller
@RequestMapping("/user")
public class OrdersController {
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private OrderDeatilsServiceImpl orderDeatilsServiceImpl;
	
	@PreAuthorize("hasRole('User')")
	@RequestMapping(value = "/myorders/{id}", method = RequestMethod.GET )
	public String viewOrders(@PathVariable int id, Model m) {
		m.addAttribute("title", "Order-WaterPlantManagment");
		System.out.println("in Order page");
		
		m.addAttribute("orders", orderServiceImpl.getOrdersByUserId(id));
		return "user/myorders";
	}
	
	@PreAuthorize("hasRole('User')")
	@RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.GET )
	public String viewOrderDetails(@PathVariable int id, Model m) {
		m.addAttribute("title", "Order-WaterPlantManagment");
		System.out.println("in Order Details page");
		
		m.addAttribute("orderDetails",orderDeatilsServiceImpl.getOrdersByOrder_Id(id));   
//		work on this
		return "user/orderDetails";
	}

}
