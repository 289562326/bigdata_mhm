package com.mhm.struct.flyweight;

import java.util.HashMap;

/**
 * 享元模式
 * 简单常用，经常使用在静态内存中
 * @author MHm
 * @date 2020-4-19 11:28
 */
public class FlyweightFactory {
    //容易造成死锁
//    private static ConcurrentHashMap<String ,Flyweight> pool = new ConcurrentHashMap();
    private static HashMap<String ,Flyweight> pool = new HashMap<>();

    public static Flyweight getFlyweight(String outterState){
        Flyweight flyweight = null;
        if(pool.containsKey(outterState)){
            flyweight = pool.get(outterState);
        }else{
            flyweight = new FlyweightImpl(outterState);
            pool.put(outterState,flyweight);
        }
        return flyweight;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i=0;i<10000;i++){
                FlyweightFactory.getFlyweight("创建对象"+i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("not used flyweight used time:"+(endTime-startTime)+" ms");

       long startTime2 = System.currentTimeMillis();
        for (int i=0;i<10;i++){
            for (int j=0;j<10000;j++){
                FlyweightFactory.getFlyweight("对象"+j);
            }
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("used time:"+(endTime2-startTime2)+" ms");
    }
}
