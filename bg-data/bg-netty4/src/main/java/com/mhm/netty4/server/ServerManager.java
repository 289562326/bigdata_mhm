package com.mhm.netty4.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mhm
 * @date 2020-4-18 9:22
 */
public class ServerManager {
    private static Map<String,Object> serverMap = new ConcurrentHashMap<String,Object>();


    public void addServer(String key ,Object obj){
        if(serverMap.get(key)==null){
            serverMap.put(key,obj);
        }else{
        }
    }
}
