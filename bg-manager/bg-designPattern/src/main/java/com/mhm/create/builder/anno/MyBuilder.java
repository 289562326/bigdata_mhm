package com.mhm.create.builder.anno;

import java.lang.annotation.*;

/**
 * 自定义注解，实现建造者模型，lombok实现原理
 * @author Mhm
 * @date 2020-4-15 21:47
 */
/**
 * @Target 约束该注解只能作用于类接口或枚举上
 * @Retention 表示这是一个运行时注解，可以通过反射技术获取注解中的信息
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBuilder {

}
