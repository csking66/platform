package com.core.autoconfigure;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.core.config.CoreConfig;
import com.core.config.Swagger2Config;

/**
* @ClassName: AutoConfiguration
* @Description: 
* @date 2018年11月16日
*
*/
@Configuration
@ImportAutoConfiguration({Swagger2Config.class, CoreConfig.class})
public class AutoConfiguration {

}
