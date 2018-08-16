package com.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.core.util.UserAuditor;

/**
 * 为了审计配置
 *适用于关系型数据库
 */
@Configuration
@EnableJpaAuditing
@ConditionalOnClass(value=JpaRepository.class, name={"org.springframework.orm.jpa.AbstractEntityManagerFactoryBean"})
@ConditionalOnProperty(name = "spring.data.jpa.audit.open", havingValue = "true", matchIfMissing = true)
public class JpaConfig {

	@ConditionalOnWebApplication
	@Bean
	public UserAuditor auditorProvider() {

		return new UserAuditor();
	}
}
