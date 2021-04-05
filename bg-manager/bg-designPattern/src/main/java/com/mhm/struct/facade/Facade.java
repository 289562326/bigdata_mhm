package com.mhm.struct.facade;

/**
 * 门面模式
 * 可以抽象外观类
 * 使用场景：为复杂的模块或者子系统提供一个供外界访问的接口
 * 子系统相对独立，一个子系统可以有多个门面，门面不参与子系统内的业务逻辑
 *
 * 优点：减少系统的相互依赖
 * 缺点：不符合开闭原则
 * @author MHm
 * @date 2020-4-19 12:59
 */
public class Facade {
    //增加和移除子系统时需要修改外观类，违背了开闭原则
    private Module module = new Module();

    /**
     * 统一提供访问系统的接口，
     */
    public void doSomething(){
        module.method();
    }

}
