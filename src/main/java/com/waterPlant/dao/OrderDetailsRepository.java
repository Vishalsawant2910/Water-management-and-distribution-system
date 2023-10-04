package com.waterPlant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.waterPlant.pojo.OrderDetails;
import com.waterPlant.pojo.Orders;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

//	
	@Query("select o from OrderDetails o where o.order_id=:order_id")
	public List<OrderDetails> findByOrder_id(int order_id);

	
}
