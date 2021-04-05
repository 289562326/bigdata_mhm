package com.mhm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等）
 * @EnableDiscoveryClient基于spring-cloud-commons
 * @EnableEurekaClient基于spring-cloud-netflix，首选eureka
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class BgCloudRestclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgCloudRestclientApplication.class, args);
	}

}
