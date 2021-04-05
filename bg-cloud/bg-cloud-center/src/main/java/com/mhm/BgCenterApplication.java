package com.mhm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 单独启动一个服务注册中心
 * @author MHm
 * @create 2019-06-07
 */
@EnableEurekaServer
@SpringBootApplication
public class BgCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgCenterApplication.class, args);
	}

}
