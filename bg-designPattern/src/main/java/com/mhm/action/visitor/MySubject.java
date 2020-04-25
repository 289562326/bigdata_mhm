package com.mhm.action.visitor;

/**
 * 访问者模式
 * 有点南段
 *
 * @author MHm
 * @date 2020-4-20 18:20
 */
public class MySubject implements Subject{
    private String name;

    public MySubject(String name) {
        this.name = name;
    }

    /**
     * 只接受访问者
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }

    @Override
    public String getField() {
        return name;
    }

    public static void main(String[] args) {
        Subject subject = new MySubject("A");
        subject.accept(new AVisitor());
        subject.accept(new BVisitor());
    }
}
