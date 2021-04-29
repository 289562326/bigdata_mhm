package com.mhm.struct.flyweight;

import java.util.HashMap;

/**
 * 享元模式
 * 简单常用，经常使用在静态内存中
 *
 * @author MHm
 * @date 2020-4-19 11:28
 */
public class FlyweightFactory {
    //容易造成死锁
    //    private static ConcurrentHashMap<String ,Flyweight> pool = new ConcurrentHashMap();
    private static HashMap<String, Flyweight> pool = new HashMap<>();

    public static Flyweight getFlyweight(String outterState) {
        Flyweight flyweight = null;
        if (pool.containsKey(outterState)) {
            flyweight = pool.get(outterState);
        } else {
            flyweight = new FlyweightImpl(outterState);
            pool.put(outterState, flyweight);
        }
        return flyweight;
    }

}
