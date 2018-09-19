package spring.pattern.proxy.service.impl;

/**
 * cglib 代理
 * 目前类不能用final 修饰
 * 方法不能用static final 修饰
 */
public class Cglib {
	
	private String name = "java";
	
	public void say(){
		System.out.println(name);
	}

}
