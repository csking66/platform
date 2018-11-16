package com.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.core.util.EnvironmentHolder;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* @ClassName: Swagger2Config
* @Description: 
* @date 2018年11月16日
*
*/
@Configuration
@EnableSwagger2
@ConditionalOnWebApplication
public class Swagger2Config extends WebMvcConfigurerAdapter{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * 正式环境api 禁掉
	 */
	@Bean
	public Docket createRestApi() {
		//是否是正式环境
		boolean apiOpen = EnvironmentHolder.isProduct();
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(apiOpen ? PathSelectors.none() : PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("后端api文档").version(EnvironmentHolder.getProjectVersion()).build();
	}
	
	
	
}
