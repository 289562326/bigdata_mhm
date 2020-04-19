package com.mhm.netty4.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by MHm on 2019/7/31.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter  {//implements ChannelOutboundHandler
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("ClientHandler Active");
//        for(int i=0;i<1000;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ctx.channel().writeAndFlush(""+i);
//        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client read complete");
//        ctx.pipeline().channel().writeAndFlush("read complete");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        System.out.println("--------");
        System.out.println("ClientHandler read Message:"+msg);
        ctx.pipeline().channel().writeAndFlush("客户端收到消息，并返回OK:"+msg);
//        ctx.fireChannelRead(msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
/*
    @Override
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress,
    ChannelPromise channelPromise) throws Exception {

    }

    @Override
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress,
    SocketAddress socketAddress1, ChannelPromise channelPromise) throws Exception {

    }

    @Override
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise)
    throws Exception {

    }

    @Override
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {

    }

    @Override
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise)
    throws Exception {

    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ClientHandler Active");
        for(int i=0;i<1000;i++){
            ctx.channel().writeAndFlush(""+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise channelPromise)
    throws Exception {
        System.out.println("--------");
        System.out.println("ClientHandler read Message:"+msg);
        ctx.pipeline().channel().writeAndFlush("客户端收到消息，并返回OK:"+msg);
    }

    @Override
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {

    }*/
}
