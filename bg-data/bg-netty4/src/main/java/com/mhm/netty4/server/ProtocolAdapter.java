package com.mhm.netty4.server;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @author MHm
 * @date 2020-4-28 21:55
 */
public class ProtocolAdapter {
    private ChannelInboundHandlerAdapter decoder;
    private ChannelOutboundHandlerAdapter encoder;

    public ProtocolAdapter(String type) {
        //可以根据协议类型采用工厂方法
        decoder = new ChannelInboundHandlerAdapter();
        encoder = new ChannelOutboundHandlerAdapter();
    }

    public ChannelInboundHandlerAdapter getDecoder() {
        return decoder;
    }

    public void setDecoder(ChannelInboundHandlerAdapter decoder) {
        this.decoder = decoder;
    }

    public ChannelOutboundHandlerAdapter getEncoder() {
        return encoder;
    }

    public void setEncoder(ChannelOutboundHandlerAdapter encoder) {
        this.encoder = encoder;
    }
}
