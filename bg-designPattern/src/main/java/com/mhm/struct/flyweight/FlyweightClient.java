package com.mhm.struct.flyweight;

/**
 * @author MHm
 * @date 2020-4-25 19:24
 */
public class FlyweightClient {
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
