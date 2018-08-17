package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.core.util.MyJdkSerializationRedisSerializer;

/**
 * maxInactiveIntervalInSeconds session 过期时间默认1800 30分钟，可修改
 * @author 王晨晨
 *
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)
public class RedisSessionConfig {

	/**
	 * redis 序列化
	 * @return
	 */
	@Bean
	RedisSerializer<Object> springSessionDefaultRedisSerializer() {

		return new MyJdkSerializationRedisSerializer();
	}
	
}
