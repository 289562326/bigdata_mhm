package com.mhm.struct.bridge;

/**
 * 抽象化角色
 * 聚合实现化角色
 *
 * @author MHm
 * @date 2020-4-19 12:01
 */
public abstract class Abstraction {
    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void method() {
        this.implementor.start();
    }

    public Implementor getImplementor() {
        return implementor;
    }
}
