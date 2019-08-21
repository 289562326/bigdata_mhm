package com.mhm.subscribe;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by MaHuiming on 2019/8/19.
 */
@Slf4j
public class Receiver {
    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
    }
}
