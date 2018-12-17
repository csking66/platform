package com.sos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
* @ClassName: SOSApplicantion
* @Description: 
* @date 2018年11月15日
*
*/
@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = {"com.domain.sos"})
public class SOSApplicantion {

	public static void main(String[] args) {
		SpringApplication.run(SOSApplicantion.class, args);
	}

}
