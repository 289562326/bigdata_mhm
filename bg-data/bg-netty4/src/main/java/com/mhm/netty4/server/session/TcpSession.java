package com.mhm.netty4.server.session;

import com.mhm.netty4.server.connect.IConnection;
import com.mhm.netty4.server.listener.ISessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author MHm
 * @date 2020-5-10 21:25
 */
public class TcpSession extends  SessionAttr{

    private final static Logger logger = LoggerFactory.getLogger(TcpSession.class);

    private String sessionId = null;

    private transient List<ISessionListener> listeners = new CopyOnWriteArrayList<ISessionListener>();

    private transient IConnection connection = null;

    private transient ISessionManager sessionManager = null;

    @Override
    public void close() {

    }

    /**
     * TCP建立连接
     */
    @Override
    public void connect() {
        /**
         * 如果正在连接或者已连接或者不可用
         */
        if(isConnecting || !isValid){
            logger.info("the session "+sessionId+"is connecting or isValid");
            return;
        }
        isConnecting = true;
        connection.connect();

    }

    @Override
    public boolean isExpire() {
        return false;
    }

    @Override
    public void setCreateTime(long createTime) {

    }

    @Override
    public void setSessionId(String sessionId) {

    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public void setSessionManager(ISessionManager sessionManager) {

    }

    @Override
    public void getSessionManager() {

    }

    @Override
    public void addSessionListener(ISessionListener sessionListener) {

    }

    @Override
    public void removeSessionListener(ISessionListener sessionListener) {

    }

    @Override
    public void setConnection(IConnection connection) {

    }

    @Override
    public IConnection getConnection() {
        return null;
    }
}
