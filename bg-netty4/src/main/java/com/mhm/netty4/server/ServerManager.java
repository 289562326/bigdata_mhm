package com.mhm.netty4.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mhm
 * @date 2020-4-18 9:22
 */
public class ServerManager {
    private static Map<String,Object> serverMap = new HashMap<String,Object>();


    public void addServer(String key ,Object obj){
        if(serverMap.get(key)==null){
            serverMap.put(key,obj);
        }else{
        }
    }
}
