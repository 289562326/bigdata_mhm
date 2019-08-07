package com.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MaHuiming on 2019/6/25.
 */
@RestController
@RefreshScope
public class TestController {

    @Autowired
    private TestService testService;

    @Value("${switchStr:not found}")
    private String switchStr;

    @RequestMapping("/test")
    public String test(){
        return testService.getSwitchStr();
    }

    @RequestMapping("/debug")
    public String debug(){
        return "debug:"+switchStr;
    }
}
