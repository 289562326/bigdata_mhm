package com.mhm.struct.decorator;

/**
 * 具体装饰者
 * 与代理模式的区别在于，此处可以实现多个装饰
 * 如果装饰模式省略抽象装饰角色后，与代理模式基本相同
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