package com.mhm.action.mediator;

/**
 * 客户
 * @author MHm
 * @date 2020-4-19 21:29
 */
public abstract class Customer {
    private String name;
    //中介
    private Mediator mediator;

    public Customer(String name) {
        this.name = name;
    }
    //发广告
    public abstract void send(String ad);
    public abstract void receive(String from,String ad);
}
