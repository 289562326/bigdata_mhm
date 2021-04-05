package com.mhm.struct.adapter.classAdapter;

import com.mhm.struct.adapter.Adaptee;
import com.mhm.struct.adapter.Target;

/**
 * 适配器角色：类间集成
 * 类适配器，耦合度高
 * 不符合合成服用原则
 * @author MHm
 * @date 2020-4-18 15:30
 */
public class Adapter extends Adaptee implements Target {
    /**
     * 可以修改实现adaterRequest完成双向适配
     */
    @Override
    public void request() {
        super.adpaterRequest();
    }

    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}
