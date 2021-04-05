package com.mhm.netty4.server.connect;

import com.mhm.netty4.server.session.ISession;
import com.mhm.netty4.server.session.TcpSessionManager;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author MHm
 * @date 2020-5-10 21:53
 */
public class TcpConnection implements IConnection{
    private ChannelHandlerContext cxt;

    protected TcpSessionManager tcpSessionManager = null;

    public TcpConnection(ChannelHandlerContext cxt) {
        this.cxt = cxt;
    }
    @Override
    public void connect() {

    }

    @Override
    public void close() {

    }

    @Override
    public String getConnectionId() {
        return null;
    }

    @Override
    public void setConnectionId(String connectionId) {

    }

    @Override
    public ISession getSession() {
        return null;
    }

    @Override
    public void setSession(ISession session) {

    }
}
