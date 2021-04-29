package com.mhm.struct.bridge;

/**
 * 桥接模式
 * 扩展实现抽象化角色
 * 关键代码：抽象类依赖实现类
 *
 * @author MHm
 * @date 2020-4-19 12:03
 */
public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    /**
     * 重写父类的方法，不符合里式替换原则
     */
    @Override
    public void method() {
        super.method();
        super.getImplementor().stop();
    }

}

