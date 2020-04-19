package com.mhm.create.singleton;

/**
 * 懒汉式，线程安全
 * Created by MHm on 2020-4-12.
 */
public class LazySyncSingleton {

    private static LazySyncSingleton lazySyncSingleton;
    private LazySyncSingleton(){

    }

    /**
     * 增加了同步方法
     * @return
     */
    public static synchronized LazySyncSingleton getInstance(){
        if(null == lazySyncSingleton){
            lazySyncSingleton = new LazySyncSingleton();
        }
        return lazySyncSingleton;
    }
}
