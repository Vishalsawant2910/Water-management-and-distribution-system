package com.waterPlant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterPlant.dao.OrderRepository;
import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;



@Service
public class OrderServiceImpl {

	@Autowired
	OrderRepository orderRepository;
	
    public List<Orders> getAllOrders() {
		
		return orderRepository.findAll();
	}
    
    public void addOrder(Orders orders) {
    	orderRepository.save(orders);
	}
    
    public void deleteOrderById(int id) {
		orderRepository.deleteById(id);
	}

	public List<Orders> getOrdersByUserId(int user_id)
	{
		return orderRepository.findByUser_id(user_id);
	}

	public Optional<Orders> getOrderById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}

	public Optional<Orders> getOrderByID(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
		
	}
	public Orders getLastOrder() {
		return orderRepository.findTopByOrderByIdDesc();
		
	}

	

}
