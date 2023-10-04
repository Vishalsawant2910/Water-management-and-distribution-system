package com.waterPlant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterPlant.dao.UserRepository;

import com.waterPlant.pojo.User;

@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;
	
    public List<User> getAllUser() 
    {
		
		return userRepository.findAll();
	}
      
     public void addUser(User user)
     {
    	 userRepository.save(user) ;
     }
     
     public void deleteUserById(int id)
     {
 		userRepository.deleteById(id);
 	 }
     
     public Optional<User> getUserById(int id)
     {
 		return userRepository.findById(id);
 	 }
     
}
