package com.mhm.action.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 对象结构角色
 * @author MHm
 * @date 2020-4-26 22:25
 */
public class ObjectStructure {
    private List<Element> elements = new ArrayList<Element>();


    public void accept(Visitor visitor){
        Iterator<Element> i = elements.iterator();
        while(i.hasNext()){
            i.next().accept(visitor);
        }
    }

    public void add(Element element){
        elements.add(element);
    }
    public void remove(Element element){
        elements.remove(element);
    }
}
