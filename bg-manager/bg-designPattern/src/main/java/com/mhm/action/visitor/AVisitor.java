package com.mhm.action.visitor;

/**
 * 具体访问者
 * @author MHm
 * @date 2020-4-20 18:18
 */
public class AVisitor implements Visitor{
    private void doSomething(){
        System.out.println("A访问者还可以做其他事情");
    }
    @Override
    public void visitor(Element element) {
        System.out.println("A可以访问："+element.getField());
        doSomething();
    }
}
