package com.mhm.netty4.server;

/**
 * @author MHm
 * @date 2020-4-28 21:17
 */
public class TcpServerContext {

    private String protocolType;

    public TcpServerContext(String protocolType) {
        this.protocolType = protocolType;

    }

    public String getProtocolType() {
        return protocolType;
    }

}
