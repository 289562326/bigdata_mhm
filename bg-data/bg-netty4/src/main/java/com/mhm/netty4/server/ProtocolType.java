package com.mhm.netty4.server;

/**
 * @author MHm
 * @date 2020-4-28 21:57
 */
public enum ProtocolType {
    IEC104("IEC104"),GW376("GW376");
    private String name;
    private ProtocolType(String name) {
        this.name = name;
    }
    public static String geProtocalType(String name) {
        for (ProtocolType c : ProtocolType.values()) {
            if (c.getName() == name) {
                return c.name;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
