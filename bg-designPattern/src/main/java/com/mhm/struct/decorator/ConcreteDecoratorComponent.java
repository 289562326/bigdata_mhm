package com.mhm.struct.decorator;

/**
 * 具体装饰构件，被装饰者
 * @author MHm
 * @date 2020-4-19 13:54
 */
public class ConcreteDecoratorComponent extends DecoratorComponent {
    @Override
    public void opreate() {
        System.out.println("具体装饰构件的操作");
    }
}
