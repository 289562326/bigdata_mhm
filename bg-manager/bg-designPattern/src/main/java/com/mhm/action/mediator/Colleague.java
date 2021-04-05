package com.mhm.action.mediator;

/**
 * 抽象同事类
 * @author MHm
 * @date 2020-4-26 21:17
 */
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();
    public abstract void send();
}
