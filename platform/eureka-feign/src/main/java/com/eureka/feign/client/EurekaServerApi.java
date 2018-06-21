package com.eureka.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.entity.User;

@FeignClient(value = "eureka-server")
public interface EurekaServerApi {
	
	@RequestMapping(value = "/hello/",method = RequestMethod.GET)
	String hello(@RequestParam("name") String name);
	
	@PostMapping("/getUser")
	User getUser(@RequestBody User user);

}
