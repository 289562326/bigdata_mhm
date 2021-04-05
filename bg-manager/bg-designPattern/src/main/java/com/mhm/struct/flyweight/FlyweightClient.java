package com.mhm.struct.flyweight;

/**
 * 享元模式
 * 使用场景：池技术
 * 优点：减少创建的对象，节省内存
 * 缺点：需要分离内部状态和外部状态，外部状态具有固化特征，不应随内部状态改变而改变，否则导致系统逻辑混乱。
 * 关键问题：解决线程安全的问题，尽量使用java基本类型作为外部状态。
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
