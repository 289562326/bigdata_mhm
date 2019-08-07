package com.mhm;

import com.mhm.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgRedisApplication {
	@Autowired
	private static RedisUtil redisUtil;
	public static void main(String[] args) {
		SpringApplication.run(BgRedisApplication.class, args);

	}

}
