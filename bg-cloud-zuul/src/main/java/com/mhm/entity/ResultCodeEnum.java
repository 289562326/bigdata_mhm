package com.mhm.entity;

/**
 * Created by MaHuiming on 2020-4-4.
 */
public enum ResultCodeEnum {

    SYSTEMERROR(500, "系统异常"),
    NORMAL(200, "正常");

    private int code;
    private String name;

    private ResultCodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static ResultCodeEnum getNameByCode(int code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (code == resultCodeEnum.getCode()) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
