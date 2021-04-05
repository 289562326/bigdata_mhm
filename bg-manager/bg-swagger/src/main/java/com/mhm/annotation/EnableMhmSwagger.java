package com.mhm.annotation;

import com.mhm.configuration.SwaggerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author MHm
 * @date 2021-4-5 9:25
 */
/**
 * @Target 位置:约束该注解只能作用于类接口或枚举上
 * @Retention 作用域:表示这是一个运行时注解，可以通过反射技术获取注解中的信息
 * @Documented  文档：注释的作用及其javadoc文档生成工具的使用
 * @Inherited  继承：可以被继承
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerConfiguration.class })
public @interface EnableMhmSwagger {
}
