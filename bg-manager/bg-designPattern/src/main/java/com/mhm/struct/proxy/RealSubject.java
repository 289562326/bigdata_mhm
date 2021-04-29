package com.mhm.struct.proxy;

/**
 * @author Mhm
 * @date 2020-4-18 15:11
 */
public class RealSubject implements Subject {
    @Override
    public void action() {
        System.out.println("controller层");
    }
}
