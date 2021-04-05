package com.mhm.netty4.server.session;

/**
 * @author MHm
 * @date 2020-5-10 19:57
 */
public interface ISessionManager {
    /**
     * 添加session
     * @param session
     */
    void addSession(ISession session);

    /**
     * 更新session
     * @param session
     */
    void updateSession(ISession session);

    /**
     * 移除session
     * @param session
     */
    void removeSession(ISession session);

    /**
     * 根据sessionId移除session
     * @param sessionId
     */
    void removeSession(String sessionId);

    /**
     * 获取session
     * @param sesionId
     * @return
     */
    ISession getSession(String sesionId);

    /**
     * 获取所有session
     * @return
     */
    ISession[] getSessions();

    /**
     * 获取session数量
     * @return
     */
    int getSessionCount();

    int getMaxInactiveInterval();

    void setMaxInactiveInterval(int interval);
}
