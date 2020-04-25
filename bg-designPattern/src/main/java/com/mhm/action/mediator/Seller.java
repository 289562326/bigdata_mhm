package com.mhm.action.mediator;

/**
 * 中介者模式
 *
 *
 * @author MHm
 * @date 2020-4-19 21:32
 */
public class Seller extends Customer{
    public Seller(String name) {
        super(name);
    }

    @Override
    public void send(String ad) {

    }

    @Override
    public void receive(String from, String ad) {

    }
}
