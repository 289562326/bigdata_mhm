package com.mhm.struct.decorator;

/**
 * 抽象装饰者
 * 和代理模式的区别？
 * @author MHm
 * @date 2020-4-19 13:56
 */
public abstract class Decorator extends DecoratorComponent{
    private DecoratorComponent decoratorComponent = null;

    //通过构造器传递被装饰者
    public Decorator(DecoratorComponent decoratorComponent) {
        this.decoratorComponent = decoratorComponent;
    }

    @Override
    public void opreate() {
        this.decoratorComponent.opreate();
    }
}
