package com.mhm.create.abstractFactory;

/**
 * 具体产品
 * @author Mhm
 * @date 2020-4-17 8:58
 */
public class Ecache extends CacheDeployment {

    @Override
    public void getDeploy() {
        System.out.println("已经创建ecache单机缓存");
    }

    @Override
    public void deplay() {
        System.out.println("创建ecache单机缓存");
    }
}
