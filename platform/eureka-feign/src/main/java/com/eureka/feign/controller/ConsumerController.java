package com.eureka.feign.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.entity.User;
import com.eureka.feign.client.EurekaServerApi;

@Api(value = "ConsumerController", tags = "消费端接口")
@RestController
public class ConsumerController {
	
	@Autowired
	private EurekaServerApi api;
	
	@ApiOperation(value = "测试数据", notes = "测试数据")
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String helloController() {
        return api.hello("cs_wang");
    }
	
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	public User getUser() {
		User user = new User();
		user.setId(1L);
		user.setName("cs_wang");
		user.setAge(30);
        return api.getUser(user);        		
    }
}
