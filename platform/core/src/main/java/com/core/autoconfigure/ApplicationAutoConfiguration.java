package com.core.autoconfigure;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.core.config.Swagger2Config;

@Configuration
@ImportAutoConfiguration({Swagger2Config.class})
public class ApplicationAutoConfiguration {

}
