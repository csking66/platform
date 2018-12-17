package com.pmis.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.sos.User;

/**
* @ClassName: LoginService
* @Description: 
* @date 2018年12月17日
*
*/
@FeignClient("sos-cs")
public interface LoginService {
	
	@PostMapping("/login")
	User login(@RequestParam(name = "accout")String accout, @RequestParam(name = "password")String password);

}
