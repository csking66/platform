package com.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * maxInactiveIntervalInSeconds session 过期时间默认1800 30分钟，可修改
 * @author 王晨晨
 *
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)
public class RedisSessionConfig {

}
