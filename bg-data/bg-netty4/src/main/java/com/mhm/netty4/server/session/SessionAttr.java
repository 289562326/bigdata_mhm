package com.mhm.netty4.server.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MHm
 * @date 2020-5-10 21:29
 */
public abstract class SessionAttr implements ISession{
    /**
     * 是否已连接
     */
    protected volatile boolean isConnecting = false;

    /**
     * 是否停止
     */
    protected  volatile boolean isStoped = false;

    /**
     * 是否可用
     */
    protected  volatile boolean isValid = false;

    protected Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

    public void setAttribute(String name,String value){

    }

    public boolean isValid(){
        if(isStoped){
            return true;
        }
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
