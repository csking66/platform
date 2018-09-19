package spring.controller;

import org.springframework.stereotype.Controller;

import spring.config.RoutingInjected;
import spring.service.HelloService;

@Controller
public class HelloController {

	@RoutingInjected
    private HelloService helloService;
	
	public void sayHello(){
        this.helloService.sayHello();
    }

    public void sayHi(){
        this.helloService.sayHi();
    }
    
}
