package com.mhm.controller;

import com.mhm.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 从配置中心获取配置参数
 * Created by MHm on 2019/6/25.
 */
@RestController
@RefreshScope
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Value("${switchStr:not found}")
    private String switchStr;

    @RequestMapping("/test")
    public String test(){
        return configService.getSwitchStr();
    }

    @RequestMapping("/debug")
    public String debug(){
        return "debug:"+switchStr;
    }
}
