package com.mhm.action.visitor;

/**
 * 访问对象
 * @author MHm
 * @date 2020-4-20 18:16
 */
public interface Subject {
    /**
     * 接受将要访问它的对象
     * @param visitor
     */
    void accept(Visitor visitor);

    /**
     * 访问者可以查看的字段
     * @return
     */
    String getField();
}
