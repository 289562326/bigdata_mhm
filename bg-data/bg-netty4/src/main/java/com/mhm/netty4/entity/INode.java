package com.mhm.netty4.entity;

/**
 * @author MHm
 * @date 2020-5-10 21:13
 */
public interface INode {
    void setValid(boolean isValid);
    boolean isValid();

    void setCreateTime(long createTime);

    void setSessionId(String sessionId);

    String getSessionId();


}
