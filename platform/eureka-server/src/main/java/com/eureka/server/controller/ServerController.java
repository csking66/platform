package com.eureka.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.entity.User;

@Api(value = "ServerController", tags = "服务端接口")
@RestController
public class ServerController {

	private final Logger logger = Logger.getLogger(getClass());

	@ApiOperation(value = "测试数据", notes = "测试数据")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(@RequestParam(value = "name") String name) {
		logger.info("这是服务端1");
		return "Hello World" + name;
	}

	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/getUser")
	public User getUser(@RequestBody User entity) {
		logger.info("这是服务端1");
		entity.setRemark("这是服务端1");
		return entity;
	}

}
