package com.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	/**
	 * restTemplate 调用，无需添加@EnableDiscoveryClient 或者EnableEurekaClient
	 * 如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
	 */
	@Autowired
    RestTemplate restTemplate;
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String helloController() {
        return restTemplate.getForEntity("http://eureka-server/hello", String.class).getBody();
    }
}
