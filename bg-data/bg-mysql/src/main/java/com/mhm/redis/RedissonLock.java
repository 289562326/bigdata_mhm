package com.mhm.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MHm
 * @date 2021-2-27 18:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {
    /**
     * 要锁哪个参数
     */
    int lockIndex() default -1;

    /**
     * 锁多久后自动释放（单位：秒）
     */
    int leaseTime() default 10;
}
