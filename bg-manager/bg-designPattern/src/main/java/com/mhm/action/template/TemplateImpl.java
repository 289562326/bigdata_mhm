package com.mhm.action.template;

/**
 * @author MHm
 * @date 2020-4-19 19:22
 */
public class TemplateImpl extends Template {
    private boolean alarmFlag = false;

    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public void preDO() {
        System.out.println("pre do");
    }

    @Override
    public void postDo() {
        System.out.println("post do");
    }

    @Override
    protected void doIt() {
        System.out.println("do it");
    }

    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    public void setAlarm(boolean isAlarm) {
        this.alarmFlag = isAlarm;
    }
}
