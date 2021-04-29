package com.mhm.action.memento;

/**
 * 备忘录模式
 * 管理者
 *
 * @author MHm
 * @date 2020-4-20 18:29
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}
