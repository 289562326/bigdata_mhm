package com.mhm.create.singleton;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 静态内部类实现
 * @date 2020-4-12 21:20
 */
public class StaticInnerClass {

    private static class InnerClass {
        private final static StaticInnerClass staticInnerClass = new StaticInnerClass();
    }

    private StaticInnerClass() {

    }

    /**
     * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
     *
     * @return
     */
    public final static StaticInnerClass getInstance() {
        return InnerClass.staticInnerClass;
    }

    public static void main(String[] args) {
        StaticInnerClass.getInstance();
    }
}
