package spring.pattern.proxy.controller;

import spring.pattern.proxy.service.impl.Cglib;
import spring.pattern.proxy.service.impl.CglibProxy;

public class ProxyController {
	
	public static void main(String[] args) {
		//静态
//		ProxyService service = new ProxyServiceImpl(new RealProxy());
//		service.say();
//		
//		ProxyService service1 = new ProxyServiceImpl(new Real2Proxy());
//		service1.say();
		
		//JDk代理
//		DynamicProxy proxy = new DynamicProxy(new RealProxy());
//		ProxyService service = (ProxyService) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{ProxyService.class}, proxy);
//		service.say();
		
		//cglib 代理
		Cglib cg = (Cglib) new CglibProxy(new Cglib()).getProxyInstance();
		cg.say();
		
	}

}
