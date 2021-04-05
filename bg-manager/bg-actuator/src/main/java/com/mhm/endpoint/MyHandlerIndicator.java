package com.mhm.endpoint;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康断点
 * Created by MHm on 2019/9/2.
 */
@Component("my")
public class MyHandlerIndicator implements HealthIndicator{
    private static final String VERSION = "V1.0.0";
    @Override
    public Health health() {
        int code = check();
        if(code == 0){
            return Health.up().withDetail("code",code).withDetail("version",VERSION).build();
        }
        return Health.down().withDetail("code",code).withDetail("version",VERSION).build();
    }

    private int check(){
        return 0;
    }
}
