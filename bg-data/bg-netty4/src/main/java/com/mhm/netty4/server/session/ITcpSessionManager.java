package com.mhm.netty4.server.session;

import com.mhm.netty4.server.listener.ISessionListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MHm
 * @date 2020-5-10 20:41
 */
public abstract class ITcpSessionManager implements ISessionManager{

    private int maxInactiveInterval = 5 * 60;

    protected Map<String, ISession> sessions = new ConcurrentHashMap<String ,ISession>();

    protected List<ISessionListener> sessionListeners = null;

    public abstract ISession createSession(String sessionId, ChannelHandlerContext ctx);

    /**
     * 添加session
     * @param session
     */
    @Override
    public synchronized void addSession(ISession session) {
        if (session == null) {
            return;
        }
        sessions.put(session.getSessionId(),session);
    }

    @Override
    public void updateSession(ISession session) {

    }

    @Override
    public void removeSession(ISession session) {

    }

    @Override
    public void removeSession(String sessionId) {

    }

    @Override
    public ISession getSession(String sesionId) {
        return null;
    }

    @Override
    public ISession[] getSessions() {
        return new ISession[0];
    }

    @Override
    public int getSessionCount() {
        return 0;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }

    public List<ISessionListener> getSessionListeners() {
        return sessionListeners;
    }

    public void setSessionListeners(List<ISessionListener> sessionListeners) {
        this.sessionListeners = sessionListeners;
    }
}
