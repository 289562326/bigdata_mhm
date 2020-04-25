package com.mhm.struct.proxy.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 真实主题与代理主题一一对应，增加真实主题也要增加代理。
 * 设计代理以前真实主题必须事先存在，不太灵活。采用动态代理模式可以解决以上问题
 * @author MHm
 * @date 2020-4-23 10:33
 */
public class DynamicProxy implements InvocationHandler {
    private DynamicSubject dynamicSubject;

    public DynamicProxy(DynamicSubject dynamicSubject) {
        this.dynamicSubject = dynamicSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object result = method.invoke(dynamicSubject,args);
        System.out.println("结束代理");
        return result;
    }

    public DynamicSubject creatObj(){
        return  (DynamicSubject) Proxy.newProxyInstance(dynamicSubject.getClass().getClassLoader(),dynamicSubject.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        DynamicSubject realDynamicSubjet = new RealDynamicSubject();
        InvocationHandler subjectProxy = new DynamicProxy(realDynamicSubjet);
        ((DynamicProxy) subjectProxy).creatObj();
    }
}
