package spring.pattern.proxy.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 代理
 */
public class DynamicProxy implements InvocationHandler{
	
	private final Object object;
	
	public DynamicProxy(Object object){
		this.object = object;
	}
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		Object result = method.invoke(object, args);
        return result;
	}

}
