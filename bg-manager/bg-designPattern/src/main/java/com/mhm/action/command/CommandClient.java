package com.mhm.action.command;

/**
 * 命令模式
 * 场景：所有认为需要使用命令的地方，比如cmd、cli
 * 优点：
 * 缺点：
 *
 * @author MHm
 * @date 2020-4-26 10:22
 */
public class CommandClient {
    public static void main(String[] args) {
        Command command = new ConcreteCommand(new Receiver());
        Invoker invoker = new Invoker(command);
        invoker.call();
    }
}
