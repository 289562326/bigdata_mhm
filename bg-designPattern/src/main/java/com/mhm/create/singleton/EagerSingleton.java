package com.mhm.create.singleton;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 饿汉式，直接初始化内存
 * @date 2020-4-12 21:10
 */
public class EagerSingleton {
    private static EagerSingleton eagerSingleton = new EagerSingleton();
    private EagerSingleton(){

    }
    public static EagerSingleton getInstance(){
        return eagerSingleton;
    }
}
