package com.mhm.action.mediator;

/**
 * @author MHm
 * @date 2020-4-26 21:19
 */
public class AColleague extends Colleague{
    @Override
    public void receive() {
        System.out.println("A同事收到请求");
    }

    @Override
    public void send() {
        System.out.println("A同事发出请求");
        mediator.dispath(this);
    }
}
