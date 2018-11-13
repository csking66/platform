package com.bpm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @ClassName: BPMApplication
* @Description: 
* @date 2018年11月12日
*
*/
@ComponentScan
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = "com.river.dao") //整合mybatis 扫描路径
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class
})//去除 spring 安全权限控制
public class BPMApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BPMApplication.class, args);
	}

}
