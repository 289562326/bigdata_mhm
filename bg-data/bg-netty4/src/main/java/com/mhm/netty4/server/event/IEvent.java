package com.mhm.netty4.server.event;

import com.mhm.netty4.server.session.ISession;

import java.util.EventObject;

/**
 * java时间模型
 * @author Mhm
 * @date 2020-4-18 14:39
 */
public abstract class IEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public IEvent(Object source) {
        super(source);
    }

    public ISession getSession(){
        return (ISession) super.getSource();
    }
}
