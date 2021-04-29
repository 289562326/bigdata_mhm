package com.mhm.action.command;

/**
 * 调用者
 *
 * @author MHm
 * @date 2020-4-26 10:25
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void call() {
        command.excute();
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
