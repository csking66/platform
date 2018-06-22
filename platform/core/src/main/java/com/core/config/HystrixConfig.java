package com.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@EnableHystrixDashboard
@ConditionalOnWebApplication
public class HystrixConfig {

}
