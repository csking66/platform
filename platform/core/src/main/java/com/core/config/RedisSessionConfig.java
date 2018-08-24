package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.core.config.session.CookieHeaderHttpSessionStrategy;
import com.core.util.RedisObjectSerializer;

/**
 * maxInactiveIntervalInSeconds session 过期时间默认1800 30分钟，可修改
 *@EnableRedisHttpSession  Spring Session Servlet filter的配置
 *@EnableCaching 开启缓存功能
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)
public class RedisSessionConfig {
	
	/**
	 * HttpSessionStrategy 接口，定义了session的基本用法
	 * 
	 * CookieHeaderHttpSessionStrategy 实现了HttpSessionStrategy 接口并且加强了request，response的用法，
	 * 还实现了HttpSessionManager 接口。
	 * HttpSessionManager 维护着一组 session Id 别名映射，以支持多个同时会话管理
	 * @return
	 */
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		CookieHeaderHttpSessionStrategy strategy = new CookieHeaderHttpSessionStrategy();
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setCookiePath("/");//设置 为 '/' 可解决同一台服务器下不同项目直接 session 共享
		cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		strategy.setCookieSerializer(cookieSerializer);
		return strategy;
	}

	/**
	 * redis 序列化
	 * @return
	 */
	@Bean
	RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new RedisObjectSerializer();
	}
	
}
