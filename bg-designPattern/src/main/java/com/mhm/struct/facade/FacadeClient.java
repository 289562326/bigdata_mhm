package com.mhm.struct.facade;

/**
 * 门面模式
 * 优点：迪米特法则的典型
 * 缺点：不符合开闭原则
 *
 * @author MHm
 * @date 2020-4-25 11:26
 */
public class FacadeClient {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doSomething();
    }
}
