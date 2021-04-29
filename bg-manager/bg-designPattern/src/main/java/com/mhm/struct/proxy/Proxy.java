package com.mhm.struct.proxy;

/**
 * 代理类
 *
 * @author Mhm
 * @date 2020-4-18 15:12
 */
public class Proxy implements Subject {
    //如果我们这个地方代理的是接口会怎么样？动态代理
    private Subject realSubject;

    //根据传入的参数，形成具体的实现类，就编程了动态代理
    public Proxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void action() {
        preAction();
        //不改变所代理类的接口，只是为了加以控制
        realSubject.action();
        postAction();
    }

    public void preAction() {
        System.out.println("pre filter before action");
    }

    public void postAction() {
        System.out.println("post filter before action");
    }

}
