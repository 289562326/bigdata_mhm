package com.mhm.netty4.server.session;

import com.mhm.netty4.server.connect.IConnection;
import com.mhm.netty4.server.connect.TcpConnection;
import com.mhm.netty4.server.listener.ISessionListener;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MHm
 * @date 2020-5-10 20:02
 */
public class TcpSessionManager extends ITcpSessionManager {

    private final static Logger logger = LoggerFactory.getLogger(TcpSessionManager.class);

    @Override
    public ISession createSession(String sessionId, ChannelHandlerContext ctx) {
        ISession session = sessions.get(sessionId);
        if (session != null) {
            logger.info("sessoin:" + sessionId + "exsits!");
            session.close();
        }
        logger.info("create new session " + sessionId + ",ctx->" + ctx.toString());
        session = new TcpSession();
        session.setSessionId(sessionId);
        session.setValid(true);
        session.setSessionManager(this);
        session.setConnection(createConnection(session, ctx));
        logger.info("create new session " + sessionId + " is successful!");
        for (ISessionListener listener : sessionListeners) {
            session.addSessionListener(listener);
        }
        logger.info("add session listener to session  " + sessionId + " listeners :" + sessionListeners);
        return session;
    }

    protected IConnection createConnection(ISession session, ChannelHandlerContext channelHandlerContext) {
        IConnection connection = new TcpConnection(channelHandlerContext);
        connection.setConnectionId(session.getSessionId());
        connection.setSession(session);
        return connection;
    }

}
