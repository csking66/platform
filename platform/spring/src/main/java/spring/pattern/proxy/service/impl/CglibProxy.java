package spring.pattern.proxy.service.impl;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


public class CglibProxy implements MethodInterceptor{
	
	private Object object;
	
	public CglibProxy(Object object){
		this.object = object;
	}
	
	public Object getProxyInstance(){
		//工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(object.getClass());
		//设置回调函数
		en.setCallback(this);
		//创建子类，（代理对象）
		return en.create();
	}
	

	@Override
	public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		Object result = method.invoke(object, args);
        return result;
	}

}
