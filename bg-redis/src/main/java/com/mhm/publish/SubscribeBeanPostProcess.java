package com.mhm.publish;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * bean初始化的时候对每个bean进行检查然后给有对应注解的方法进行订阅处理
 * Created by MHm on 2019/8/19.
 */
public class SubscribeBeanPostProcess implements BeanPostProcessor,ApplicationContextAware {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        Method[] methods = aClass.getMethods();
        for(Method method:methods){
            RedisListener annotation = method.getAnnotation(RedisListener.class);
            if(annotation!=null ){
                String channels = StringUtils.isBlank(annotation.channel())? "*":annotation.channel();
                System.out.println("添加一个订阅："+aClass.getSimpleName()+"#"+method.getName());
            }
        }
        return null;
    }
}
