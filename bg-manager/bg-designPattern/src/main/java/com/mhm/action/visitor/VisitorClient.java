package com.mhm.action.visitor;

/**
 * 访问者模式
 * 优点：
 * 缺点：
 * 场景：
 * 将对数据的操作与数据结构进行分离，是行为类模式中最复杂的一种模式
 * @author MHm
 * @date 2020-4-25 22:07
 */
public class VisitorClient {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();

        //抽象元素
        Element elementA = new ConcreteElement("A");
        Element elementB = new ConcreteElement("B");
        objectStructure.add(elementA);
        objectStructure.add(elementB);
        objectStructure.accept(new AVisitor());
        objectStructure.accept(new BVisitor());
        //扩展、如果新增了C，则只需要C实现Visitor接口，不要改变其他代码

//        //抽象元素
//        Element elementA = new ConcreteElement("A");
//        //接受访问者A，违反了依赖倒转，
//        elementA.accept(new AVisitor());
//        //接受访问者B
//        elementA.accept(new BVisitor());
    }
}
