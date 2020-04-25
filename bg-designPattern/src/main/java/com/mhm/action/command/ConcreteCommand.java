package com.mhm.action.command;

/**
 *
 * 和装饰者模式有啥区别？
 * @author MHm
 * @date 2020-4-20 13:49
 */
public class ConcreteCommand implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void excute() {
        receiver.action();
    }
}
