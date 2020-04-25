package com.mhm.action.chainOfResponsibility;

/**
 * @author MHm
 * @date 2020-4-20 13:43
 */
public class HandlerClient {
    public static void main(String[] args) {
        Handler serverHandler = new ServerHandler();
        Handler heartBeatHandler = new HeartBeatHandler();
        serverHandler.addLast(heartBeatHandler);
        serverHandler.handlerRequest("客户端发送的消息");
    }
}
