package com.mhm.netty4.server.session;

import com.mhm.netty4.entity.INode;
import com.mhm.netty4.server.connect.IConnection;
import com.mhm.netty4.server.listener.ISessionListener;

/**
 * @author MHm
 * @date 2020-4-29 11:36
 */
public interface ISession extends INode {

    void close();

    void connect();

    boolean isExpire();

    void setSessionManager(ISessionManager sessionManager);
    void getSessionManager();

    void addSessionListener(ISessionListener sessionListener);

    void removeSessionListener(ISessionListener sessionListener);

    void setConnection(IConnection connection);

    IConnection getConnection();
}
