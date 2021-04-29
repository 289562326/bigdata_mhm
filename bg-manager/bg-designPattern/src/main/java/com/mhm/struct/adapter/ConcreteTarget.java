package com.mhm.struct.adapter;

/**
 * 目标角色的实现类
 *
 * @author MHm
 * @date 2020-4-19 14:23
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("我原来是220V,现在用380V适配");
    }
}
