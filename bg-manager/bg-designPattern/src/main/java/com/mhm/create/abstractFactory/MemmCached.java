package com.mhm.create.abstractFactory;

/**
 * @author Mhm
 * @date 2020-4-17 9:05
 */
public class MemmCached extends CacheDeployment {

    @Override
    public void getDeploy() {
        System.out.println("已经创建了分布式缓存MemmCached");
    }

    @Override
    public void deplay() {
        System.out.println("创建了分布式缓存MemmCached");
    }
}
