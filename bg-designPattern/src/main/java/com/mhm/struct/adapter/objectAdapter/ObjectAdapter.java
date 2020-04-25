package com.mhm.struct.adapter.objectAdapter;

import com.mhm.struct.adapter.Adaptee;
import com.mhm.struct.adapter.Target;

/**
 * 对象适配器：是对象的合成关系
 * 和代理模式很像，区别在于适配者不需要实现target接口
 * 和装饰模式也像
 * 关键代码
 * 适配器继承或依赖已有的对象，实现想要的目标接口
 * 使用场景：在详设阶段最好不要考虑
 *
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
