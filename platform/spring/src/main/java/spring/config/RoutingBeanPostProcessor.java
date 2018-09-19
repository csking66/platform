package spring.config;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RoutingBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(RoutingInjected.class)) {
				
			}
		}
		
		
		return bean;
	}

}
