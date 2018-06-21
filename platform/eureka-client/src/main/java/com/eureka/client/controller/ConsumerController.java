package com.eureka.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.domain.entity.User;

@Api(value = "ConsumerController", tags = "消费端接口")
@RestController
public class ConsumerController {

	/**
	 * restTemplate 调用，无需添加@EnableDiscoveryClient 或者EnableEurekaClient
	 * 如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
	 */
	@Autowired
    RestTemplate restTemplate;
	
	@ApiOperation(value = "测试数据", notes = "测试数据")
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String helloController() {
        return restTemplate.getForEntity("http://eureka-server/hello?name=cs_wang", String.class).getBody();
    }
	
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	public User getUser() {
		User user = new User();
		user.setId(1L);
		user.setName("cs_wang");
		user.setAge(30);
		ResponseEntity<User> response = restTemplate.postForEntity("http://eureka-server/getUser", user,User.class);
        return response.getBody();        		
    }
	
}
