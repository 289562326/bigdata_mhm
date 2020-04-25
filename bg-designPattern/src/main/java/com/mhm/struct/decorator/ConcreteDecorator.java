package com.mhm.struct.decorator;

/**
 * 具体装饰者，与代理模式的区别在于，此处可以实现多个装饰
 * 装饰模式应该为所装饰的对象增强功能；代理模式对代理的对象施加控制，但不对对象本身的功能进行增强。
 * 装饰模式是对功能的增强
 * @author MHm
 * @date 2020-4-19 14:01
 */
public class ConcreteDecorator extends  Decorator{
    public ConcreteDecorator(DecoratorComponent decoratorComponent) {
        super(decoratorComponent);
    }

    private void method(){
        System.out.println("具体的装饰1");
    }

    /**
     * 重写父类的方法，破坏了里式替换原则
     */
    @Override
    public void opreate() {
        super.opreate();
        this.method();
    }


}
class ConcreteDecorator2 extends  Decorator {
    public ConcreteDecorator2(DecoratorComponent decoratorComponent) {
        super(decoratorComponent);
    }

    private void method() {
        System.out.println("具体的装饰2");
    }

    /**
     * 重写父类的方法，破坏了里式替换原则
     */
    @Override
    public void opreate() {
        super.opreate();
        this.method();
    }
}