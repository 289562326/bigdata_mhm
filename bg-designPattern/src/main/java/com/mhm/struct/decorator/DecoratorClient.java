package com.mhm.struct.decorator;

/**
 * 装饰模式
 *
 * 使用场景：需要扩展一个类的功能，或者给一个类增加附加功能
 * JAVA I/O是典型的装饰模式
 * 需要动态的给一个对象增加功能，还可以再动态的撤销
 * 优点：装饰类Decorator和被装饰类ConcreteDecoratorComponent可以相互独立发展，而不会耦合
 * 缺点：多层的装饰是比较复杂的，像俄罗斯套娃一样，尽量减少装饰类的数量，减低系统复杂度
 *
 * @author MHm
 * @date 2020-4-25 10:55
 */
public class DecoratorClient {

    public static void main(String[] args) {
        //
        DecoratorComponent decoratorComponent = new ConcreteDecoratorComponent();
        //第一次装饰
        decoratorComponent = new ConcreteDecorator(decoratorComponent);
        //第二次被装饰
        decoratorComponent = new ConcreteDecorator2(decoratorComponent);
        decoratorComponent.opreate();
    }
}
