package com;

import com.mhm.netty4.server.NettyServer;
import com.mhm.netty4.server.TcpServerContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgNetty4Application implements CommandLineRunner {

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


    public static void main(String[] args) {
        SpringApplication.run(BgNetty4Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //由客户端决定采用的是什么编解码方式，解析的是什么协议
        System.out.println("start");
        TcpServerContext tcpServerContext = new TcpServerContext("IEC104");
        NettyServer nettyServer= new NettyServer(port,tcpServerContext);
        nettyServer.start();
//        Thread.sleep(30000);
//        nettyServer.stop();
//        System.out.println("end");
    }
}
