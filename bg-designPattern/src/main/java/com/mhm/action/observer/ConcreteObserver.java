package com.mhm.action.observer;

/**
 * 具体的被观察者
 * @author MHm
 * @date 2020-4-19 20:55
 */
public class ConcreteObserver implements Observer{
    @Override
    public void update() {
        System.out.println("接收到了配置文件发生了变化，修改本地文件");
    }
}
