package com.eureka.server.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
	
	 private final Logger logger = Logger.getLogger(getClass());
	 
	 @RequestMapping(value = "/hello", method = RequestMethod.GET)
	 public String index() {
		 logger.info("这是服务端");
		 return "Hello World";
	 }

}
