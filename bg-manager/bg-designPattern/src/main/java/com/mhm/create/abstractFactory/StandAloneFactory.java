package com.mhm.create.abstractFactory;

/**
 * 具体工厂-单机部署
 * 使用本地cache和关系型数据库
 *
 * @author Mhm
 * @date 2020-4-16 19:30
 */
public class StandAloneFactory implements AbstractFactory {
    @Override
    public CacheDeployment createCache(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (CacheDeployment) Class.forName(className).newInstance();
    }

    @Override
    public RDBMSDeployment createRDBMS(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return (RDBMSDeployment) loader.loadClass(className).newInstance();
        //        return (RDBMSDeployment) Class.forName(className).newInstance();
    }
}
