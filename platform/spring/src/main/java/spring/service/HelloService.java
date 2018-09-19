package spring.service;

import spring.config.RoutingSwitch;


/**
 * 测试BeanPostProcessor
 *
 */

public interface HelloService {

	@RoutingSwitch("A")
	void sayHello();
	
    void sayHi();
}
