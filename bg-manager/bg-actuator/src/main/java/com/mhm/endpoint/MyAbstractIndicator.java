package com.mhm.endpoint;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Created by MHm on 2019/9/2.
 */
@Component("my2")
public class MyAbstractIndicator extends AbstractHealthIndicator {
    private static final String VERSION = "V1.0.0";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = check();
        if (code == 0) {
            Health.up().withDetail("code", code).withDetail("version", VERSION).build();
        } else {
            Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
    }

    private int check() {
        return 0;
    }
}
