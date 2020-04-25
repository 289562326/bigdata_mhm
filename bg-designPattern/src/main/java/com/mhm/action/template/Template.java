package com.mhm.action.template;

/**
 * @author MHm
 * @date 2020-4-19 15:35
 */
public abstract class Template {
    public abstract void init();
    public abstract void preDO();
    public abstract void postDo();
    public final void request(){
        init();
        preDO();
        postDo();
    }
}
