package com.eureka.client.service;

import com.domain.dto.LoginParam;
import com.domain.entity.User;

public interface UserService {

	User getUser(User user);
	
	User addUser(User user);
	
	User login(LoginParam param);
}
