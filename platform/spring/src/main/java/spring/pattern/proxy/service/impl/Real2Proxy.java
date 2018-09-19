package spring.pattern.proxy.service.impl;

import spring.pattern.proxy.service.ProxyService;

public class Real2Proxy implements ProxyService{

	private String name = "beijing";
	@Override
	public void say() {
		System.out.println(name);
		
	}

}
