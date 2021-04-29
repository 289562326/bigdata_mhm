package com.mhm.action.visitor;

/**
 * 访问者B
 *
 * @author MHm
 * @date 2020-4-20 18:19
 */
public class BVisitor implements Visitor {
    private void doSomething() {
        System.out.println("B访问者也可以做其他事情");
    }

    @Override
    public void visitor(Element element) {
        System.out.println("B访问了：" + element.getField());
        doSomething();
    }

}
