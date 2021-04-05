package com.mhm.netty4.server.event;

/**
 * @author MHm
 * @date 2020-5-10 20:44
 */
public abstract class ITcpEvent extends IEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ITcpEvent(Object source) {
        super(source);
    }


}
