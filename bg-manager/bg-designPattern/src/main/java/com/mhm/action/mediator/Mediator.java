package com.mhm.action.mediator;

/**
 * 中介抽象类
 * @author MHm
 * @date 2020-4-19 21:28
 */
public interface Mediator {
    void register(Colleague colleague);
    void dispath(Colleague colleague);
}
