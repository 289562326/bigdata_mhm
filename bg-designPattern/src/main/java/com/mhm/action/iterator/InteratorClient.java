package com.mhm.action.iterator;

/**
 * 迭代器模式
 * @author MHm
 * @date 2020-4-20 13:57
 */
public class InteratorClient {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("1");
        aggregate.add("2");
        aggregate.add("3");
        aggregate.add("4");
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()){
            Object ob=iterator.next();
            System.out.print(ob.toString()+"\t");
        }
    }
}
