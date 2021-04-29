package com.mhm.action.chainOfResponsibility;

/**
 * 抽象处理者角色
 * 参考netty的ChannelPipeLine
 *
 * @author MHm
 * @date 2020-4-20 13:32
 */
public abstract class Handler {
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public Handler addLast(Handler handler) {
        this.handler = handler;
        return this.handler;
    }

    public abstract void handlerRequest(String msg);
}
