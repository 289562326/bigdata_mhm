package com.mhm.action.observer;

/**
 * 具体被观察者
 *
 * @author MHm
 * @date 2020-4-19 21:00
 */
public class ServerSubject extends PropertiesSubject {
    //修改了配置文件通知所有的客户端
    public void modifyProperties() {
        System.out.println("服务端修改了配置文件");
        super.notifyObservers();
    }
}
