package com.es.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.common.Result;
import com.domain.dto.LoginParam;
import com.domain.entity.User;
import com.es.service.UserService;

@Api(value = "用户", tags = "用户")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * consumes MediaType.APPLICATION_FORM_URLENCODED_VALUE  还是以参数的形式传进来
	 * 
	 */
	@ApiOperation(value = "登录接口", notes = "密码是原文，默认语言时：简体中文（zh_CN）。")
	//@RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping("/user/login")
	public Result<User> doLogin(@RequestBody LoginParam loginParam, HttpServletRequest request) {
		return login(loginParam, request);
	}
	
	private Result<User> login(LoginParam loginParam, HttpServletRequest request) {
		Assert.notNull(loginParam.getAccount(), "account is required");
		User user = userService.login(loginParam.getAccount(), loginParam.getPassword());
		request.getSession().setAttribute(loginParam.getAccount(), user);
		return new Result<User>(user);
	}
	
	@ApiOperation(value = "新增数据",notes = "新增数据")
	@PostMapping("/user/save")
	public Result<User> save(@RequestBody User entity) {
		userService.save(entity);
		return new Result<User>(entity);
	}

}
