package com.mhm.struct.flyweight;

/**
 * 抽象享元角色
 * @author MHm
 * @date 2020-4-19 11:29
 */
public abstract class Flyweight {
    private String innerState;
    protected final String outterState;

    public Flyweight(String outterState) {
        this.outterState = outterState;
    }
    public abstract  void operate();

    public String getInnerState() {
        return innerState;
    }

    public void setInnerState(String innerState) {
        this.innerState = innerState;
    }
}
