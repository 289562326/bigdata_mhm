package com.mhm.struct.decorator;

/**
 * 具体装饰者，与代理模式的区别在于，此处可以实现多个装饰
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

    public static void main(String[] args) {
        DecoratorComponent decoratorComponent = new ConcreteDecoratorComponent();
        //第一次装饰
        decoratorComponent = new ConcreteDecorator(decoratorComponent);
        //第二次被装饰
        decoratorComponent = new ConcreteDecorator2(decoratorComponent);
        decoratorComponent.opreate();
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