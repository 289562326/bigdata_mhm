package com.mhm.netty4.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接数计数
 * @author MHm
 * @date 2020-4-27 14:08
 */
public class ConnectionCountHandler extends ChannelInboundHandlerAdapter {
    private AtomicInteger nConnection = new AtomicInteger();

    public ConnectionCountHandler() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("connections: " + nConnection.get());
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        nConnection.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        nConnection.decrementAndGet();
    }
}
