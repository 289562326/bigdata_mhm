package com.mhm.action.mediator;

/**
 * @author MHm
 * @date 2020-4-26 21:21
 */
public class BColleague extends Colleague {
    @Override
    public void receive() {
        System.out.println("B同事收到请求");
    }

    @Override
    public void send() {
        System.out.println("B同事发出请求");
        mediator.dispath(this);
    }
}
