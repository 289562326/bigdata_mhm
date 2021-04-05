package com.mhm.create.builder;

/**
 * 建造者模式
 * 配合模板方法使用
 * @author MHm
 * @date 2020-4-25 20:28
 */
public class BuilderClient {

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
