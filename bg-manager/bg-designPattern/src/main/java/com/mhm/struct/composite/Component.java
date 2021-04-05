package com.mhm.struct.composite;

/**
 * 抽象构件
 * @author MHm
 * @date 2020-4-19 13:12
 */
public interface Component {
    void add(Component c);
    void remove(Component c);
    Component getChildren(int index);
    void operation();
}
