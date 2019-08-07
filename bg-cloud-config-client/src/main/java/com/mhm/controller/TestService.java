package com.mhm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * Created by MaHuiming on 2019/6/25.
 */
@Service
@RefreshScope
public class TestService {

    @Value("${switchStr}")
    public String switchStr;

    public String getSwitchStr(){
        return switchStr;
    }
}
