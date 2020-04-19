package com.mhm.struct.adapter.objectAdapter;

import com.mhm.struct.adapter.Adaptee;
import com.mhm.struct.adapter.Target;

/**
 * 对象适配器
 * 和代理模式很像，区别在于适配者不需要实现target接口
 * 和装饰模式也像
 * @author MHm
 * @date 2020-4-19 14:48
 */
public class ObjectAdapter implements Target {
    //适配者
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.adpaterRequest();
    }

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
