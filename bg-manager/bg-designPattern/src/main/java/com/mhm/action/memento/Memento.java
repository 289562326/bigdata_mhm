package com.mhm.action.memento;

/**
 * 备忘录
 *
 * @author MHm
 * @date 2020-4-20 18:26
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
