package com.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MaHuiming on 2019/6/14.
 */
@org.springframework.web.bind.annotation.RestController
public class TestController {
    private int m = 0;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test(){
        int index = m;
        for(int i=m;i<index+10;i++){
            redisTemplate.convertAndSend("mytopic", "这是我发第"+i+"条的消息啊");
        }
        redisTemplate.opsForValue().set("mhm","mhm");
        return redisTemplate.opsForValue().get("mhm");
    }
}
