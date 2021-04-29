package com.mhm.action.template;

/**
 * @author MHm
 * @date 2020-4-19 15:35
 */
public abstract class Template {
    public abstract void init();

    public abstract void preDO();

    public abstract void postDo();

    protected abstract void doIt();

    public final void request() {
        init();
        if (isAlarm()) {
            doIt();
        }
        preDO();
        postDo();
    }

    //扩展，钩子方法
    protected boolean isAlarm() {
        return true;
    }
}
