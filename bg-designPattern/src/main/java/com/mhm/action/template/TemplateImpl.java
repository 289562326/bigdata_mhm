package com.mhm.action.template;

/**
 * @author MHm
 * @date 2020-4-19 19:22
 */
public class TemplateImpl extends Template{
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

    public static void main(String[] args) {
        Template template = new TemplateImpl();
        template.request();
    }
}
