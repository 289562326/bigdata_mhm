package com.mhm.create.abstractFactory;

/**
 * 抽象产品-关系型数据库部署
 *
 * @author Mhm
 * @date 2020-4-17 9:06
 */
public abstract class RDBMSDeployment implements Deployment {
    public abstract void getDeploy();
}
