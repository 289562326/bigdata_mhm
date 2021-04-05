package com.mhm.struct.proxy.invocation;

/**
 * @author MHm
 * @date 2020-4-23 10:33
 */
public class RealDynamicSubject implements DynamicSubject{
    @Override
    public void method() {
        System.out.println("真实的我");
    }
}
