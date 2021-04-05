package com.mhm.netty4.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 通道初始化实现类
 * @ChannelHandler.Sharable这个注解网上的说法不一
 *
 * Created by MHm on 2019/7/31.
 */
@ChannelHandler.Sharable
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {

    private TcpServerContext tcpServerContext;

    public ServerChannelInitializer(TcpServerContext tcpServerContext) {
        this.tcpServerContext = tcpServerContext;
    }
    //采用适配器或者工厂模式，选择合适的协议解析
    //或者动态代理

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        String protocalType = tcpServerContext.getProtocolType();
        ProtocolAdapter protocolAdapter = ProtocolFactory.getMode(protocalType);
//        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
//        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("decoder",protocolAdapter.getDecoder());
        channel.pipeline().addLast("encoder",protocolAdapter.getEncoder());
        channel.pipeline().addLast(new ServerHandler());
//        channel.pipeline().addLast(new NextServerHandler());
    }


}
