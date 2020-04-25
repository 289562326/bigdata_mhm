package com.mhm.netty4.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 *  通道初始化实现类
 * Created by MHm on 2019/7/31.
 */
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {

    //采用适配器或者工厂模式，选择合适的协议解析
    //或者动态代理

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new ServerHandler());
//        channel.pipeline().addLast(new NextServerHandler());
    }


}
