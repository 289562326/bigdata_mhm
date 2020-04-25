package com.mhm.action.observer;

/**
 * 观察者模式
 * @author MHm
 * @date 2020-4-24 13:46
 */
public class ObserverClient {

    /**
     * 应用场景：服务端添加了很多客户端，服务端修改了配置文件，通知客户端
     *
     * @param args
     */
    public static void main(String[] args) {

        ServerSubject serverSubject = new ServerSubject();
        Observer observer = new ConcreteObserver();
        serverSubject.addObserver(observer);
        serverSubject.modifyProperties();
    }
}
