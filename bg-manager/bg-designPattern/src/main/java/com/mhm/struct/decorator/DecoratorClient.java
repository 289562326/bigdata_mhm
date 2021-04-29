package com.mhm.struct.decorator;

/**
 * 装饰模式
 * <p>
 * 使用场景：需要扩展一个类的功能，或者给一个类增加附加功能
 * JAVA I/O是典型的装饰模式
 * 需要动态的给一个对象增加功能，还可以再动态的撤销
 * 优点：装饰类Decorator和被装饰类ConcreteDecoratorComponent可以相互独立发展，而不会耦合
 * 缺点：多层的装饰是比较复杂的，像俄罗斯套娃一样，尽量减少装饰类的数量，减低系统复杂度
 * 关键代码
 * 1、DecoratorComponent 类充当抽象角色，不应该具体实现。
 * 2、修饰类引用和继承 Component 类，具体扩展类重写父类方法
 * <p>
 * * 装饰模式应该为所装饰的对象增强功能；代理模式对代理的对象施加控制，但不对对象本身的功能进行增强。
 * * 装饰模式是对功能的增强
 *
 * @author MHm
 * @date 2020-4-25 10:55
 */
public class DecoratorClient {

    public static void main(String[] args) {
        //具体被装饰者
        DecoratorComponent decoratorComponent = new ConcreteDecoratorComponent();
        //第一次装饰
        decoratorComponent = new ConcreteDecorator(decoratorComponent);
        //第二次被装饰
        decoratorComponent = new ConcreteDecorator2(decoratorComponent);
        decoratorComponent.opreate();
    }
}
