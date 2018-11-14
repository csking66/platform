package com.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.sos.User;
import com.sos.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @ClassName: LoginController
* @Description: 
* @date 2018年11月14日
*
*/
@Api("登录接口")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@ApiOperation("登录")
	@PostMapping("sos/login")
	public User login(@RequestParam("accout") String accout, @RequestParam("password") String password) {
		return loginService.login(accout, password);
	}
	
}
