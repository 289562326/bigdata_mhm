package com.mhm.struct.bridge;

/**
 * @author MHm
 * @date 2020-4-19 12:01
 */
public abstract class Abstraction {
    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    public void run(){
        this.implementor.start();
    }

    public Implementor getImplementor() {
        return implementor;
    }
}
