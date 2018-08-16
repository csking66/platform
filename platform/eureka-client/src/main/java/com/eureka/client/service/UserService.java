package com.eureka.client.service;

import com.domain.entity.User;

public interface UserService {

	User getUser(User user);
	
	User addUser(User user);
}
