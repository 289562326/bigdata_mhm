package com.mhm.struct.composite;

/**
 * 树状节点
 *
 * @author MHm
 * @date 2020-4-19 13:14
 */
public class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChildren(int index) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("this name:" + this.name + "被调用");
    }
}
