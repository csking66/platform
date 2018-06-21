package com.eureka.feign.client.impl;

import org.springframework.stereotype.Component;

import com.domain.entity.User;
import com.eureka.feign.client.EurekaServerApi;

@Component
public class EurekaServerApiImpl implements EurekaServerApi {

	@Override
	public String hello(String name) {
		
		return "sorry "+ name;
	}

	@Override
	public User getUser(User user) {
		user.setRemark("sorry!");
		return user;
	}

}
