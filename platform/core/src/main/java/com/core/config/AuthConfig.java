package com.core.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.domain.common.Pagination;
import com.domain.common.Result;

/**
 * 数据权限 定义aop切面，拦截数据权限
 * 使用@Before在切入点开始处切入内容
         使用@After在切入点结尾处切入内容
         使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
         使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
         使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Configuration
public class AuthConfig {

	final static Logger logger = LoggerFactory.getLogger(AuthConfig.class);
	
	@Pointcut("execution(* com..*.controller..get*(..))")
    public void auth() {

    }
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	@AfterReturning(value = "auth()", returning = "result")
	 public Object around(Object result) throws Throwable {		 
		 if(result instanceof Result){
			 Object value = ((Result<?>) result).getData();
			 if(value instanceof Pagination){
				 Pagination pagination = (Pagination<?>) value;
				 List<?> list = pagination.getData();
				 //要显示的字段
				 List<String> includeFields = pagination.getIncludeFields();
				 if(CollectionUtils.isNotEmpty(includeFields)){
					 List newDataList = new ArrayList<>();
					 for (Object one : list) {
						 newDataList.add(processInclude(one, includeFields));
					 }
					 pagination.setData(newDataList);
				 }			 
			 }
		 }
		 return result;
	 }

	 
	 /**
	  * 只返回显示的字段
	  * @param one
	  * @param includeFields
	  * @return
	  */
	private Object processInclude(Object one, List<String> includeFields) {
		JSONObject newData = new JSONObject();
		for(String fieldName : includeFields){
			Object obj = null;
			//区分字段是一个一个保存的，还是数组保存的
			if(fieldName.indexOf("[") < 0){								
				try {
					Field field = one.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					 obj = field.get(one);
				} catch (Exception e) {
					//可能继承父类，字段没有的字段，但是子类可以获取父类的get方法，通过方法取值
					try {
						Method method = one.getClass().getMethod("get" + StringUtils.capitalize(fieldName));
						obj = method.invoke(one);
					} catch (Exception e1) {
						logger.info(fieldName + one.getClass() + "动态获取值出错" );
						continue;
					}
				}				
				newData.put(fieldName, obj);
			}
		}
		return newData;
	}
	
}
