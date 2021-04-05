package com.mhm.netty4.server.listener;

import com.mhm.netty4.server.event.IEvent;

/**
 * @author MHm
 * @date 2020-5-10 20:05
 */
public interface ISessionListener extends IListener {

    void sessionCreated(IEvent event);

    void sessionDestoryed(IEvent event);
}
