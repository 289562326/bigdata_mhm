package com.mhm.create.builder;

/**
 *  产品角色
 * @author MHm
 * @date 2020-4-21 11:57
 */
public class BuilderProduct {
    private String partA;
    private String partB;
    private String partC;
    public void setPartA(String partA)
    {
        this.partA=partA;
    }
    public void setPartB(String partB)
    {
        this.partB=partB;
    }
    public void setPartC(String partC)
    {
        this.partC=partC;
    }
    public void show()
    {
        //显示产品的特性
        System.out.println(this.partA+this.partB+this.partC);
    }
}
