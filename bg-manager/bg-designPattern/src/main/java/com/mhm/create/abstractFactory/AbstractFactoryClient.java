package com.mhm.create.abstractFactory;

/**
 * @author Mhm
 * @date 2020-4-18 11:13
 */
public class AbstractFactoryClient {
    public static void main(String[] args)
    throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        StandAloneFactory standAloneFactory = new StandAloneFactory();
        CacheDeployment ecache = standAloneFactory.createCache("com.mhm.create.abstractFactory.Ecache");
        ecache.deplay();
        RDBMSDeployment mysql = standAloneFactory.createRDBMS("com.mhm.create.abstractFactory.Mysql");
        mysql.deplay();

        ClusterFactory clusterFactory = new ClusterFactory();
        CacheDeployment memmCached = clusterFactory.createCache("com.mhm.create.abstractFactory.MemmCached");
        memmCached.deplay();
        RDBMSDeployment ddm = standAloneFactory.createRDBMS("com.mhm.create.abstractFactory.DDM");
        ddm.deplay();
    }
}
