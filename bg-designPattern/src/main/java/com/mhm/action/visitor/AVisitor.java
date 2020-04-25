package com.mhm.action.visitor;

/**
 * @author MHm
 * @date 2020-4-20 18:18
 */
public class AVisitor implements Visitor{
    @Override
    public void visitor(Subject subject) {
        System.out.println("A可以访问："+subject.getField());
    }
}
