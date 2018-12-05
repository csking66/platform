package com.core.util;

import org.springframework.core.env.Environment;

import com.domain.common.util.SpringUtils;

/**
* @ClassName: EnvironmentHolder
* @Description: 
* @date 2018年11月16日
*
*/

public class EnvironmentHolder {

	//可以获取properties 
	private static Environment environment;
	
	private static final String profile = "spring.cloud.config.profile";
	
	private static final String product = "prd";

	public static Environment getEnvironment() {
		if (environment == null) {
			environment = SpringUtils.getBean(Environment.class);
		}
		return environment;
	}

	public static void setEnvironment(Environment environment) {
		EnvironmentHolder.environment = environment;
	}
	
	public static boolean isProduct() {

		return product.equalsIgnoreCase(getProfileName());
	}

	public static String getProfileName() {
		
		return getEnvironment().getProperty(profile);
	}

	public static String getApplicationName() {

		return getEnvironment().getProperty("spring.application.name");
	}

	public static String getProjectVersion() {

		return getEnvironment().getProperty("project.version");
	}

	public static String getProperty(String key) {

		return getEnvironment().getProperty(key);
	}
	
	
}
