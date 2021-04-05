package com.mhm.action.template;

/**
 * 模板方法
 * 缺点：违反了开闭原则
 * @author MHm
 * @date 2020-4-25 22:32
 */
public class TemlateClient {
    public static void main(String[] args) {
        TemplateImpl template = new TemplateImpl();
        template.setAlarm(false);
        template.request();
    }
}
