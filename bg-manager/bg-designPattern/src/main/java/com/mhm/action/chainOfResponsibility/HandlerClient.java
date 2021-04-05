package com.mhm.action.chainOfResponsibility;

/**
 * 责任链模式
 * 场景：servlet的filter、dubbo的filter
 * 优点：
 * 缺点：
 * 1、不能保证每个请求一定被处理
 * 2、对比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响
 * 3、责链建立的合理性要靠客户端来保证
 * @author MHm
 * @date 2020-4-20 13:43
 */
public class HandlerClient {
    public static void main(String[] args) {
        //java多态
        Handler serverHandler = new ServerHandler();
        //心跳
        Handler heartBeatHandler = new HeartBeatHandler();
        //sever端处理
        serverHandler.addLast(heartBeatHandler);
        serverHandler.handlerRequest("客户端发送的消息");
    }
}
