package com.mhm.action.mediator;

/**
 * 中介者模式
 * @author MHm
 * @date 2020-4-26 21:24
 */
public class MediatorClient {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague a,b;
        a = new AColleague();
        b = new BColleague();
        mediator.register(a);
        mediator.register(b);
        a.send();
        b.send();
    }
}
