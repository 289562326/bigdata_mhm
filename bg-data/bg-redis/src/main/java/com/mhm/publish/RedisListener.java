package com.mhm.publish;

import java.lang.annotation.*;

/**
 * 自定义监听注解
 * Created by MHm on 2019/8/19.
 */
@Target({ ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisListener {

    /**
     * 监听的通道名称
     * @return
     */
    String channel() default "*";
}
