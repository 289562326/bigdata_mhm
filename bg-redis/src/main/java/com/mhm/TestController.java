package com.mhm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MaHuiming on 2019/6/14.
 */
@org.springframework.web.bind.annotation.RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test(){
        redisTemplate.opsForValue().set("mhm","mhm");
        return redisTemplate.opsForValue().get("mhm");
    }
}
