package com.pmis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
* @ClassName: PMISApplicantion
* @Description: 项目管理工程
* @date 2018年12月17日
*
*/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PMISApplicantion {

	public static void main(String[] args) {
		SpringApplication.run(PMISApplicantion.class, args);

	}

}
