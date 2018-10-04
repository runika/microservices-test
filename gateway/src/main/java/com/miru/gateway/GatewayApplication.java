package com.miru.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableSwagger2
public class GatewayApplication {
	public static void main(String[] args) {
		
		new SpringApplication(GatewayApplication.class).run(args);
	}
}
