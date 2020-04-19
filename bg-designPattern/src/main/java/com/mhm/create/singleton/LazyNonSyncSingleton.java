package com.mhm.create.singleton;

/**
 *  懒汉式，线程不安全
 *  因为是延迟加载lazy loading，所以叫懒汉式
 * Created by MHm on 2020-4-12.
 */
public class LazyNonSyncSingleton {
    //不创建对象，延迟加载
    private static LazyNonSyncSingleton lazyNonSyncSingleton;

    private LazyNonSyncSingleton(){

    }

    public static LazyNonSyncSingleton getInstance(){
        if(null == lazyNonSyncSingleton){
            lazyNonSyncSingleton = new LazyNonSyncSingleton();
        }
        return lazyNonSyncSingleton;
    }
}
