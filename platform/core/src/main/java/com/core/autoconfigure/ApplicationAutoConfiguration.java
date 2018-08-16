package com.core.autoconfigure;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.core.config.HystrixConfig;
import com.core.config.JpaConfig;
import com.core.config.RedisSessionConfig;
import com.core.config.Swagger2Config;

@Configuration
@ImportAutoConfiguration({Swagger2Config.class,HystrixConfig.class,RedisSessionConfig.class,JpaConfig.class})
public class ApplicationAutoConfiguration {

}
