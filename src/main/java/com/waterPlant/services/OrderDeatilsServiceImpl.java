package com.waterPlant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterPlant.dao.OrderDetailsRepository;
import com.waterPlant.pojo.OrderDetails;
import com.waterPlant.pojo.Orders;

@Service
public class OrderDeatilsServiceImpl {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	public void addOderDetails(OrderDetails orderDetails) {
		orderDetailsRepository.save(orderDetails);
		
	}
	
	public List<OrderDetails> getOrdersByOrder_Id(int order_id)
	{
		return orderDetailsRepository.findByOrder_id(order_id);
	}
	
	public void deleteOrderDetailsById(int id) {
//		orderDetailsRepository.deleteById(id);
		orderDetailsRepository.deleteById(id);
	}

}
