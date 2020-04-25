package com.mhm.struct.decorator;

/**
 * 抽象装饰者，如果就一个装饰类可以没有抽象角色
 * 和代理模式的区别？只是运行时才指定构造器的参数是具体的那个对象
 * 静态代理在代理类的关系在编译时就确定了
 *
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
