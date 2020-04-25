package com.mhm.action.visitor;

/**
 * @author MHm
 * @date 2020-4-20 18:19
 */
public class BVisitor implements Visitor {
    @Override
    public void visitor(Subject subject) {
        System.out.println("B访问了："+subject.getField());
    }
}
