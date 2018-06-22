package com.eureka.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.domain.entity.User;
import com.eureka.client.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * restTemplate 调用，无需添加@EnableDiscoveryClient 或者EnableEurekaClient
	 * 如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
	 */
	@Autowired
    RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "resultError")
	public User getUser(User user) {
		ResponseEntity<User> response = restTemplate.postForEntity("http://eureka-server/getUser", user,User.class);
		return response.getBody();
	}
	
	public User resultError(User user) {
		user.setRemark("sorry,error!");
		return user;
	}

}
