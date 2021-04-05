package com.mhm.struct.proxy.invocation;

import java.lang.reflect.InvocationHandler;

/**
 * @author MHm
 * @date 2020-4-26 9:04
 */
public class DynamicProxyClient {
    public static void main(String[] args) {
        //需要代理的对象
        DynamicSubject realDynamicSubjet = new RealDynamicSubject();
        InvocationHandler subjectProxy = new DynamicProxy(realDynamicSubjet);
        ((DynamicProxy) subjectProxy).creatObj();
    }
}
