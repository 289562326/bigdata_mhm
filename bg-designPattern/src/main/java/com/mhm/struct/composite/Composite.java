package com.mhm.struct.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模型
 * 优点：
 * 缺点：使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒转原则
 *
 * @author MHm
 * @date 2020-4-19 13:15
 */
public class Composite implements Component{
    /**
     * 树枝内部组合该接口，并且含有内部属性 List，里面放 Component。
     */
    private List<Component> list = new ArrayList<Component>();
    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    @Override
    public Component getChildren(int index) {
        return list.get(index);
    }


    @Override
    public void operation() {
        for(Component c:list){
            c.operation();
        }
    }

    public static void main(String[] args) {
        Component composite = new Composite();
        Component composite2 = new Composite();
        Component leaf1 = new Leaf("节点1");
        Component leaf2 = new Leaf("节点2");
        Component leaf3 = new Leaf("节点3");
        composite.add(composite2);
        composite.add(leaf1);
        composite2.add(leaf2);
        composite2.add(leaf3);
        composite.operation();
    }
}
