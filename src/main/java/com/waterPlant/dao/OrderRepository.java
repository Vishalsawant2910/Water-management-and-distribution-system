package com.waterPlant.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;
import com.waterPlant.pojo.User;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	
	@Query("select o from Orders o where o.user_id=:user_id")
	public List<Orders> findByUser_id(int user_id);
	
//	@Query("select o from Orders ORDER BY id DESC LIMIT 1")
//	public Orders getLastOrder();
	
	public Orders findTopByOrderByIdDesc();

	
	
	

}
