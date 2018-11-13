package com.bpm.config;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @ClassName: ActitytiDataSourceConfig
 * @Description:
 * @date 2018年11月12日
 *
 */

@Configuration
public class ActitytiDataSourceConfig extends AbstractProcessEngineAutoConfiguration {

	private static final Logger log = LogManager.getLogger(ActitytiDataSourceConfig.class);

	@Bean(name = "activitiDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.activiti")
	public DataSource activitiDataSource() {
		log.info("activitiDataSource 初始化...");
		return new DruidDataSource();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(activitiDataSource());
	}

	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
		SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
		configuration.setDataSource(activitiDataSource());
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		configuration.setJobExecutorActivate(true);
		configuration.setTransactionManager(transactionManager());
		return configuration;
	}

}
