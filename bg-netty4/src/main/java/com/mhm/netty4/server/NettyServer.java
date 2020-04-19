package com.mhm.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * TCP服务
 * Created by MHm on 2019/7/31.
 */
@lombok.extern.slf4j.Slf4j
@Service
public class NettyServer implements IServer {
    @Value("${netty.port}")
    private int port;

    @Value("${netty.ip}")
    private String ip;

    @Value("${netty.muiltPort}")
    private boolean muiltPort;

    @Value("${netty.beginPort}")
    private int beginPort;

    @Value("${netty.nPort}")
    private int nPort;

    private ServerManager serverManager;
    //logger
    private void start(InetSocketAddress address) {
        //多线程模式处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);//可以根据机器核心*2设置
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
            //指定通道类型为NioServerSocketChannel,一种异步模式
            .channel(NioServerSocketChannel.class).localAddress(address).childHandler(new ServerChannelInitializer())
            //指定此套接口排队的最大连接个数
            .option(ChannelOption.SO_BACKLOG, 2014)
            //保持连接生命，不因空闲而断开
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            //防止数据传输延迟 如果false的话会缓冲数据达到一定量在flush,降低系统网络调用（具体场景）
            .option(ChannelOption.TCP_NODELAY, true)
            //Netty实现了一个Java版的Jemalloc内存管理库 ByteBuf内存池 要搭配ReferenceCountUtil.release(msg);不然造成内存泄漏;
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            //容量动态调整的接收缓冲区分配器 以节约内存
            .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
            ChannelFuture future = null;
            // 绑定端口，开始接收进来的连接
            if(isMuiltPort()){
                for (int i = 0; i < nPort; i++) {
                    int port = beginPort + i;
                    future = bootstrap.bind(port).sync();
                    log.info("Server start listen at " + address.getHostString() + ":" + port);
                }
            }else{

                future = bootstrap.bind(address).sync();
                log.info("Server start listen at " + address.getHostString() + ":" + address.getPort());
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }finally {
            try {
                bossGroup.shutdownGracefully().sync(); ////关闭EventLoopGroup，释放掉所有资源包括创建的线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {
        InetSocketAddress address = new InetSocketAddress(ip, port);
        start(address);
    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }

    public boolean isMuiltPort() {
        return muiltPort;
    }

    public void setMuiltPort(boolean muiltPort) {
        this.muiltPort = muiltPort;
    }
}
