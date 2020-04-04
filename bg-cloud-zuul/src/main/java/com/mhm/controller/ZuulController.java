package com.mhm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MaHuiming on 2020-4-4.
 */
@RestController
public class ZuulController {

    @Value("${server.port}")
    String port;

    @RequestMapping(value="/zuulclient")
    public String index() {
        return "index port："+port + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
