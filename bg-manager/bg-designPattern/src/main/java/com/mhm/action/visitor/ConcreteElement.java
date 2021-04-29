package com.mhm.action.visitor;

/**
 * 具体元素、访问对象
 *
 * @author MHm
 * @date 2020-4-20 18:20
 */
public class ConcreteElement implements Element {
    private String name;

    public ConcreteElement(String name) {
        this.name = name;
    }

    /**
     * 每个元素都定义了一个访问者，只接受访问者
     * 关键代码：在数据结构类中接受访问者，把自身传递过去，也就是让访问者访问本身这个对象
     *
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

}
