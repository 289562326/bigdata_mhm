package com.mhm.action.memento;

/**
 * 备忘录模式
 *
 * @author MHm
 * @date 2020-4-24 13:45
 */
public class MementoClient {
    public static void main(String[] args) {
        Originator or = new Originator();
        Caretaker cr = new Caretaker();
        or.setState("S0");
        System.out.println("初始状态:" + or.getState());
        cr.setMemento(or.create()); //保存状态
        or.setState("S1");
        System.out.println("新的状态:" + or.getState());
        or.restore(cr.getMemento()); //恢复状态
        System.out.println("恢复状态:" + or.getState());
    }
}
