package com.mhm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MHm on 2019/6/12.
 */
@Api(tags = "debug")
@RestController
@RequestMapping(value = "/debug")
public class TestControler {

    @ApiOperation(value = "debug")
    @RequestMapping()
    public String test(@RequestParam(value = "name") String name) {
        //从请求头中取出token
        return "hello:" + name;
    }

}
