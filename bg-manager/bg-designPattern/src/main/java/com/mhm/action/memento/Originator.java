package com.mhm.action.memento;

/**
 * 发起人
 *
 * @author MHm
 * @date 2020-4-20 18:27
 */
public class Originator {
    private String state;

    public Memento create() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        this.setState(memento.getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
