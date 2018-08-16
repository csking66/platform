package com.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.domain"})
public class EsApplication {

	public static void main(String[] args) {
		 SpringApplication.run(EsApplication.class, args);
	}

}
