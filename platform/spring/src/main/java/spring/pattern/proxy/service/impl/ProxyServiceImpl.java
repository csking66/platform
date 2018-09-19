package spring.pattern.proxy.service.impl;

import spring.pattern.proxy.service.ProxyService;

public class ProxyServiceImpl implements ProxyService{

	private ProxyService service;
	
	public ProxyServiceImpl(ProxyService service){
		this.service = service;
	}
	
	@Override
	public void say() {
		service.say();
		
	}

}
