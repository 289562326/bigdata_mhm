package com.mhm.netty4.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHm on 2019/7/31.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { //implements ChannelOutboundHandler
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static Map<String, Channel> maps = new HashMap<String, Channel>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());//加入ChannelGroup
        maps.put(ctx.channel().id().toString(), ctx.channel());
        System.out.println(ctx.channel().id() + " connected," + "Online: " + channels.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id() + " left ," + "Online: " + channels.size());
        maps.remove(ctx.channel().id().toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive----->");
        ctx.pipeline().channel().writeAndFlush("服务端主动发");
//        for(int i=0;i<1000;i++){
//            ctx.channel().writeAndFlush(""+i);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//         }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("read complete");
//        ctx.pipeline().channel().writeAndFlush("OK");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead......");
        System.out.println(ctx.channel().remoteAddress() + "----->Server :" + msg.toString());
        ctx.pipeline().channel().writeAndFlush("服务端返回OK");
       /* ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        int currentLen = bytes.length;
        byte[] profix = ByteUtil.subBytes(bytes, 0, 1);
        int header = ByteUtil.byte1ToInt(profix);

        System.out.println(Analysis.analysis(ByteUtil.toHexString(bytes)));*/

        //将客户端的信息直接返回写入ctx
//        ctx.channel().write("server say :" + msg);
//        ctx.channel().flush();

//        for(int i=0;i<1000;i++){
//            ctx.pipeline().channel().writeAndFlush(""+i);
//            //            ctx.flush();
//            Thread.sleep(1000);
//        }

//        for (Map.Entry<String, Channel> c : maps.entrySet()) {
//            c.getValue().pipeline().writeAndFlush("ok");
//            //刷新缓存区
//            //            ctx.flush();
//        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

//        Channel c = ctx.channel();
//        c.close().addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if (future.isSuccess()){
//                    channels.remove(future.channel());
//                    System.out.println(ctx.channel().id() + " left ," + "Online: " + channels.size());
//                }
//                else{
//                    System.out.println("FAILED to close channel");
//                }
//            }
//        });
        ctx.close();
    }

    public static Map<String, Channel> getMaps() {
        return maps;
    }
}
