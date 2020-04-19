package com.mhm.struct.bridge;

/**
 * @author MHm
 * @date 2020-4-19 12:03
 */
public class RefinedAbstraction extends Abstraction{
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    /**
     * 重写父类的方法，不符合里式替换原则
     */
    @Override
    public void run() {
        super.run();
        super.getImplementor().stop();
    }

    public static void main(String[] args) {
        Implementor implementor = new ConcreteImpl();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.run();
    }
}

