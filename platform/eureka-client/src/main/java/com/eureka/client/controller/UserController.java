package com.eureka.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.common.Result;
import com.domain.entity.User;
import com.eureka.client.service.UserService;

@Api(value = "UserController", tags = "用户接口")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@ApiOperation(value = "获取用户", notes = "获取用户")	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public Result<User> addUser(HttpSession session) {
		User user = new User();
		user.setAccount("123");
		user.setName("cs_wang");
		user.setPassword("123");
		user.setAge(30);
		user.setAddress(session.getId());
		return new Result<User>(userService.addUser(user));
	}
}
