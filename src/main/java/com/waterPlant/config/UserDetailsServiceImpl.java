package com.waterPlant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.waterPlant.dao.UserRepository;
import com.waterPlant.pojo.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
				User user = userRepository.getUserByUserName(username);
				if(user==null)
				{
					throw new UsernameNotFoundException("could not found user!!");
				}
				
				CustomeUserDetails customUserDetails=new CustomeUserDetails(user);
				return customUserDetails;
	}

}