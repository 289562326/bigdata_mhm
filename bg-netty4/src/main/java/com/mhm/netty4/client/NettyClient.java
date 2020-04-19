package com.mhm.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by MHm on 2019/7/31.
 */
public class NettyClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "2404"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        sendMessage("hhhh");
    }
    public static void sendMessage(String content) throws InterruptedException{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast("decoder", new StringDecoder());
                    p.addLast("encoder", new StringEncoder());
                    p.addLast(new ClientHandler());
                }
            });

            Channel channel  = b.connect(HOST, PORT).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            while(true){
//                try {
//                    channel.writeAndFlush(in.readLine() + "\r\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
