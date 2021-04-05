package com.mhm.action.chainOfResponsibility;

/**
 * @author MHm
 * @date 2020-4-20 13:41
 */
public class HeartBeatHandler extends Handler{
    @Override
    public void handlerRequest(String msg) {
        System.out.println("发送心跳到客户端");
        if(getHandler()!=null){
            System.out.println("处理下一个流程");
            getHandler().handlerRequest(msg);
        }
    }
}
