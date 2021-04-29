package com.mhm.create.singleton;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 双重校验锁
 * @date 2020-4-12 21:14
 */
public class DoubleCheckedLocking {
    private volatile static DoubleCheckedLocking doubleCheckedLocking;

    private DoubleCheckedLocking() {

    }

    /**
     * 双检锁，高性能
     *
     * @return
     */
    public static DoubleCheckedLocking getInstance() {
        if (null == doubleCheckedLocking) {
            synchronized (DoubleCheckedLocking.class) {
                if (null == doubleCheckedLocking) {
                    doubleCheckedLocking = new DoubleCheckedLocking();
                }
            }
        }
        return doubleCheckedLocking;
    }
}
