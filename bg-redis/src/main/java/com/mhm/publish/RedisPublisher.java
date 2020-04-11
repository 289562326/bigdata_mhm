package com.mhm.publish;

import com.mhm.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by MHm on 2019/8/19.
 */
public class RedisPublisher implements Publisher {

    @Autowired
    private RedisUtil  redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void publish(String channel, String message) {
        //发布消息
    }
}
