package com.mhm.create.singleton;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 不能通过反射来调用私有方法，相对安全
 * @date 2020-4-12 21:24
 */
public enum EnumSingleton {
    INSTANCE;

    public void getInstance() {

    }
}
