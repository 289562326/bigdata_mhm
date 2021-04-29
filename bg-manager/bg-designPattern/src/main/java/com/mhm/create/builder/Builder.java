package com.mhm.create.builder;

/**
 * 抽象建造者
 *
 * @author MHm
 * @date 2020-4-21 11:30
 */
public abstract class Builder {
    //创建产品对象
    protected BuilderProduct builderProduct = new BuilderProduct();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    //返回产品对象
    public BuilderProduct getBuilderProduct() {
        return builderProduct;
    }
}
