package com.mhm.utils;

import java.nio.ByteBuffer;

/**
 * @author MHm
 * @date 2021-2-25 10:18
 */
public class OffHeapUtil {
    public static void main(String[] args) {
        ByteBuffer.allocate(1);
        ByteBuffer.allocateDirect(1);
    }
}
