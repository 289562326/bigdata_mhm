package com.mhm.netty4.server;

import com.mhm.netty4.server.message.IEC104MesaageDecoder;
import com.mhm.netty4.server.message.IEC104MessageEncoder;

/**
 * @author MHm
 * @date 2020-4-28 22:06
 */
public class ProtocolFactory {
    public static ProtocolAdapter getMode(String type){
        ProtocolAdapter result = new ProtocolAdapter(type);
        switch (type.toUpperCase()){
            case "IEC104":
                result.setEncoder(new IEC104MessageEncoder());
                result.setDecoder(new IEC104MesaageDecoder());
                return result;
            case "MQTT":
                return result;
        }
        return null;
    }
}
