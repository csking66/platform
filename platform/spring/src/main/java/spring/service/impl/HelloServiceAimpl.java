package spring.service.impl;

import org.springframework.stereotype.Service;

import spring.service.HelloService;

@Service
public class HelloServiceAimpl implements HelloService{

	@Override
	public void sayHello() {
		System.out.println("HelloA");
	}

	@Override
	public void sayHi() {
		System.out.println("HiA");
	}

}
