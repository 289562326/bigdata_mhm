package com.mhm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 配置文件信息后去测试
 * Created by MHm on 2019/6/25.
 */
@Service
@RefreshScope
public class ConfigService {

    @Value("${switchStr}")
    public String switchStr;

    public String getSwitchStr(){
        return switchStr;
    }
}
