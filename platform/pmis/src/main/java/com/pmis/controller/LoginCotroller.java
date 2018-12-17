package com.pmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.sos.User;
import com.pmis.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @ClassName: LoginCotroller
* @Description: 
* @date 2018年12月17日
*
*/
@Api(value = "LoginCotroller", tags = "消费端接口")
@RestController
public class LoginCotroller {
	
	@Autowired
	private LoginService loginService;

	@ApiOperation("登录接口")
	@PostMapping("/login")
	public User login(@RequestParam(name = "accout")String accout, @RequestParam(name = "password")String password) {		
		return loginService.login(accout, password);
	}

}
