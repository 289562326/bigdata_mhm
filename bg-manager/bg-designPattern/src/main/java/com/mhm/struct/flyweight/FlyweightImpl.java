package com.mhm.struct.flyweight;

/**
 * @author MHm
 * @date 2020-4-19 11:33
 */
public class FlyweightImpl extends Flyweight {
    public FlyweightImpl(String outterState) {
        super(outterState);
    }

    @Override
    public void operate() {
        //
        System.out.println("子类实现:" + this.outterState);
    }
}
