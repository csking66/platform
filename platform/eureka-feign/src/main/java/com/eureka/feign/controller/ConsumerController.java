package com.eureka.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.feign.client.EurekaServerApi;

@RestController
public class ConsumerController {
	
	@Autowired
	private EurekaServerApi api;
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String helloController() {
        return api.hello("cs_wang");
    }
}
