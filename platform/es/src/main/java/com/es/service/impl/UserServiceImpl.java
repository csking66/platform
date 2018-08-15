package com.es.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entity.User;
import com.es.repository.UserRepository;
import com.es.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User login(String account, String password) {		
		return userRepository.findByAccountAndPassword(account, password);
	}

}
