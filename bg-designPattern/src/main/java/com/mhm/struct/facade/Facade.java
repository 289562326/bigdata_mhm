package com.mhm.struct.facade;

/**
 * 门面模式
 * 这种情况通常我们用代理模式去实现
 *
 * @author MHm
 * @date 2020-4-19 12:59
 */
public class Facade {
    private Module module = new Module();

    public void doSomething(){
        module.method();
    }

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doSomething();
    }
}
