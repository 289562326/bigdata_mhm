package com.mhm.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    //可以根据机器核心*2设置
    private static final int BOSS_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int WORKER_THREAD_SIZE = 4;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_GROUP_SIZE);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(WORKER_THREAD_SIZE);

    private ServerManager serverManager;

    @Override
    public void init(InetSocketAddress address) {
        //多线程模式处理
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
            if (isMuiltPort()) {
                for (int i = 0; i < nPort; i++) {
                    int port = beginPort + i;
                    future = bootstrap.bind(port).sync();
                    ChannelFuture channelFuture = future.addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (future.isSuccess()) {
                                log.info("Server start listen at " + address.getHostString() + ":" + port);
                            } else {
                                log.info("Server failed listen at " + address.getHostString() + ":" + port);
                                throw new Exception("Server start fail !", future.cause());
                            }
                        }
                    });

                }
            } else {
                future = bootstrap.bind(address).sync();
                ChannelFuture channelFuture = future.addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            log.info("Server start listen at " + address.getHostString() + ":" + address.getPort());
                        } else {
                            log.info("Server start listen at " + address.getHostString() + ":" + address.getPort());
                            throw new Exception("Server start fail !", future.cause());
                        }
                    }
                });

            }
            //
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            try {
                bossGroup.shutdownGracefully().sync(); ////关闭EventLoopGroup，释放掉所有资源包括创建的线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        InetSocketAddress address = null;
        if (StringUtils.isEmpty(ip)) {
            address = new InetSocketAddress(ip, port);
        } else {
            address = new InetSocketAddress(port);
        }
        init(address);
    }

    @Override
    public void stop() {
        try {
            bossGroup.shutdownGracefully().sync(); //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            workerGroup.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMuiltPort() {
        return muiltPort;
    }

    public void setMuiltPort(boolean muiltPort) {
        this.muiltPort = muiltPort;
    }
}