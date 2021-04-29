package com.mhm.action.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MHm
 * @date 2020-4-20 13:56
 */
public class ConcreteAggregate implements Aggregate {
    private List<Object> list = new ArrayList<Object>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return (new ConcreteIterator(list));
    }
}
