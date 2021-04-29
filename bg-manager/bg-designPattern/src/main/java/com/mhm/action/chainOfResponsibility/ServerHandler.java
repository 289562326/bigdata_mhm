package com.mhm.action.chainOfResponsibility;

/**
 * @author MHm
 * @date 2020-4-20 13:35
 */
public class ServerHandler extends Handler {
    @Override
    public void handlerRequest(String msg) {
        System.out.println("处理server端消息");
        if (getHandler() != null) {
            getHandler().handlerRequest(msg);
        }
    }
}
