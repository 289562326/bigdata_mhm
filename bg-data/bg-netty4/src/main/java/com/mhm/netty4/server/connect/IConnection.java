package com.mhm.netty4.server.connect;

import com.mhm.netty4.server.session.ISession;

/**
 * @author MHm
 * @date 2020-4-29 11:36
 */
public interface IConnection {

    void connect();

    void close();

    String getConnectionId();

    void setConnectionId(String connectionId);

    ISession getSession();

    void setSession(ISession session);


}
