package com.mhm.create.abstractFactory;

/**
 * 抽象工厂
 *
 * @author Mhm
 * @date 2020-4-16 19:27
 */
public interface AbstractFactory {
    CacheDeployment createCache(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    RDBMSDeployment createRDBMS(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
