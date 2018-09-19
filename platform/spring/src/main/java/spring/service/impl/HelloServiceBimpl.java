package spring.service.impl;

import org.springframework.stereotype.Service;

import spring.service.HelloService;

@Service
public class HelloServiceBimpl implements HelloService {

	@Override
	public void sayHello() {
		System.out.println("HelloB");
	}

	@Override
	public void sayHi() {
		System.out.println("HiB");
	}

}
