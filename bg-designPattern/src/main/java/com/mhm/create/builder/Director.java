package com.mhm.create.builder;

/**
 * 指挥者
 * @author MHm
 * @date 2020-4-21 11:32
 */
public class Director {
    private Builder builder;
    public Director(Builder builder)
    {
        this.builder=builder;
    }
    //产品构建与组装方法
    public BuilderProduct construct()
    {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getBuilderProduct();
    }

    public static void main(String[] args) {
        //构建者
        Builder builder=new ConcreteBuilder();
        //指挥者
        Director director=new Director(builder);
        //构建产品
        BuilderProduct product=director.construct();
        product.show();
    }
}
