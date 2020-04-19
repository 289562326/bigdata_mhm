package com.mhm.netty4.server;

/**
 * 服务类
 * @author Mhm
 * @date 2020-4-17 13:51
 */
public interface IServer {
    void init();
    void start();
    void stop();
    void restart();
}
