package spring.pattern.proxy.service.impl;

import spring.pattern.proxy.service.ProxyService;

public class RealProxy implements ProxyService{

	private String name = "shanghai";
	@Override
	public void say() {
		System.out.println(name);
		
	}

}
