package com.eureka.server.backup.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
	
	 private final Logger logger = Logger.getLogger(getClass());
	 
	 @RequestMapping(value = "/hello", method = RequestMethod.GET)
	 public String sayHello(@RequestParam(value = "name") String name) {
		 logger.info("这是服务端2");
		 return "Hello World" + name;
	 }

}
