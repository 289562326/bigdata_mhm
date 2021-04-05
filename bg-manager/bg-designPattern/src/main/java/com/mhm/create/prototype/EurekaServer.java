package com.mhm.create.prototype;

import java.io.Serializable;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020-4-14 22:15
 */
public class EurekaServer implements Cloneable,Serializable {
    private String sid;
    private String name;

    @Override
    public String toString() {
        return "EurekaServer{" + "sid='" + sid + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
