package com.mhm.create.abstractFactory;

/**
 * @author Mhm
 * @date 2020-4-17 9:01
 */
public class DDM extends RDBMSDeployment {
    @Override
    public void deplay() {
        System.out.println("创建了分布式式数据库DDIM");
    }

    @Override
    public void getDeploy() {
        System.out.println("已经创建了分布式式数据库DDIM");
    }
}
