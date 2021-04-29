package com.mhm.create.abstractFactory;

/**
 * 具体产品
 *
 * @author Mhm
 * @date 2020-4-17 9:00
 */
public class Mysql extends RDBMSDeployment {

    @Override
    public void deplay() {
        System.out.println("创建mysql数据库");
    }

    @Override
    public void getDeploy() {
        System.out.println("已经创建mysql数据库");
    }
}
