package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.core.util.SpringUtils;

/**
* @ClassName: CoreConfig
* @Description: 
* @date 2018年11月16日
*
*/
@Configuration
public class CoreConfig {

	@Bean
	public SpringUtils getSpringUtils() {
		
		return new SpringUtils();
	}
}
