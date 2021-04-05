package com.mhm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign-server的微服务使用fegin调用
 * Created by MHm on 2019/6/14.
 */
@RestController
public class TestController {

@RequestMapping(value = "/test")
    public String test(){
        return "test";
    }
}
