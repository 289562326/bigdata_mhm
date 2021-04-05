package com.mhm.action.iterator;

/**
 * 抽象聚合
 * @author MHm
 * @date 2020-4-20 13:55
 */
public interface Aggregate {
    void add(Object obj);
    void remove(Object obj);
    Iterator getIterator();
}
