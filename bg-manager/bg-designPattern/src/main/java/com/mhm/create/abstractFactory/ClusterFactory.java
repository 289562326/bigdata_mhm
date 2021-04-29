package com.mhm.create.abstractFactory;

/**
 * 具体工厂-分布式产品
 *
 * @author Mhm
 * @date 2020-4-16 19:28
 */
public class ClusterFactory implements AbstractFactory {
    @Override
    public MemmCached createCache(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (MemmCached) Class.forName(className).newInstance();
    }

    @Override
    public RDBMSDeployment createRDBMS(String className)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (RDBMSDeployment) Class.forName(className).newInstance();
    }
}
