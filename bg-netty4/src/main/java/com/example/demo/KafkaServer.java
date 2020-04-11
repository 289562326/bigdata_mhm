package com.example.demo;

import com.mhm.netty.server.ServerHandler;

/**
 * Created by MHm on 2019/8/13.
 */
public class KafkaServer {
    public static void send(String msg){
    }


    public void receive(String msg){
        ServerHandler.getMaps().get("192.168.0.1").pipeline().write(msg);


    }
}
