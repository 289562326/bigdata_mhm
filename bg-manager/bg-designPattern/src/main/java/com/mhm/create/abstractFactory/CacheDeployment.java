package com.mhm.create.abstractFactory;

/**
 * 抽象产品-缓存数据库部署
 *
 * @author Mhm
 * @date 2020-4-17 8:59
 */
public abstract class CacheDeployment implements Deployment {
    public abstract void getDeploy();
}
