package com.mhm.create.builder;

/**
 * 具体构建者
 *
 * @author MHm
 * @date 2020-4-21 11:31
 */
public class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        builderProduct.setPartA("建造 PartA");
    }

    @Override
    public void buildPartB() {
        builderProduct.setPartA("建造 PartB");
    }

    @Override
    public void buildPartC() {
        builderProduct.setPartA("建造 PartC");
    }
}
