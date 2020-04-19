package com.mhm.struct.bridge;

/**
 * @author MHm
 * @date 2020-4-19 12:02
 */
public class ConcreteImpl implements Implementor{
    @Override
    public void start() {
        System.out.println("服务器启动");
    }

    @Override
    public void stop() {
        System.out.println("服务器停止");
    }
}
