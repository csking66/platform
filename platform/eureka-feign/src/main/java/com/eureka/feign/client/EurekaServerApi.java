package com.eureka.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureka-server")
public interface EurekaServerApi {
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	String hello();

}
