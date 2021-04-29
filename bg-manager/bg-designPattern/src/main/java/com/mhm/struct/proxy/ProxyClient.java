package com.mhm.struct.proxy;

/**
 * 代理模式（委托模式）
 * 扩展：强制代理、动态代理
 * 使用场景：struts2的form元素映射、spring AOP
 * <p>
 * 1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
 * 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
 * 关键代码：实现与被代理对象组合
 *
 * @author MHm
 * @date 2020-4-25 19:25
 */
public class ProxyClient {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.action();
    }
}
