package com.mhm.publish;

import redis.clients.jedis.JedisPubSub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by MaHuiming on 2019/8/19.
 */
@lombok.extern.slf4j.Slf4j
public class RedisSubscribe extends JedisPubSub {

    private Object target;
    private Method method;

    public RedisSubscribe(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    @Override
    public void onMessage(String channel, String message) {
        try {
            method.invoke(target, channel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("订阅通道"+channel);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("取消订阅" + channel);
    }
}
