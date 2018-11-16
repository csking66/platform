package com.sos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
* @ClassName: SOSApplicantion
* @Description: 
* @date 2018年11月15日
*
*/
@SpringBootApplication
@EntityScan(basePackages = {"com.domain.sos"})
public class SOSApplicantion {

	public static void main(String[] args) {
		SpringApplication.run(SOSApplicantion.class, args);
	}

}
