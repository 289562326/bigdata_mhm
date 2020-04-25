package com.mhm.struct.bridge;

/**
 * 桥接模式
 * 优点：抽象和实现的分离
 * 缺点：会增加系统的理解与设计难度
 *
 * @author MHm
 * @date 2020-4-25 11:14
 */
public class BridgeClient {
    public static void main(String[] args) {
        //实现化角色
        Implementor implementor = new ConcreteImpl();
        //抽象化角色
        Abstraction abstraction = new RefinedAbstraction(implementor);
        //执行
        abstraction.method();
    }
}
