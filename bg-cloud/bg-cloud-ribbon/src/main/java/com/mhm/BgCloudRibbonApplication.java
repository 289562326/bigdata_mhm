package com.mhm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BgCloudRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgCloudRibbonApplication.class, args);
	}

}
