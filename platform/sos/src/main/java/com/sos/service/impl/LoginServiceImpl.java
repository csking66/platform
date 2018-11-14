package com.sos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.sos.User;
import com.sos.repository.UserRepository;
import com.sos.service.LoginService;

/**
* @ClassName: LoginServiceImpl
* @Description: 
* @date 2018年11月14日
*
*/
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User login(String accout, String password) {
		
		return userRepository.findAccountAndPassword(accout, password);
	}

}
