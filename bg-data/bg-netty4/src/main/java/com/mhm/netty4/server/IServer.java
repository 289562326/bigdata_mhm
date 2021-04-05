package com.mhm.netty4.server;

import java.net.InetSocketAddress;

/**
 * 服务类抽象接口
 * @author Mhm
 * @date 2020-4-17 13:51
 */
public interface IServer {
    void init(InetSocketAddress address);
    void start();
    void stop();
}
