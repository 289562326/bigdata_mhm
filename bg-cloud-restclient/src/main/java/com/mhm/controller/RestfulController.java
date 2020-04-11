package com.mhm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MHm on 2020-4-4.
 */
@RestController
public class RestfulController {

    @Value("${server.port}")
    String port;

    @RequestMapping(value="/api/restclient")
    public String index() {
        return "index port："+port+" time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
