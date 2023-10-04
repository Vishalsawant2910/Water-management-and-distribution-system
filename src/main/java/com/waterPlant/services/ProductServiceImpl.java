package com.waterPlant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterPlant.dao.ProductRepository;
import com.waterPlant.pojo.Product;

@Service
public class ProductServiceImpl {
	
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}

	public Optional<Product> getProductById(long id){
		return productRepository.findById(id);
	}
	
	
	 
}
