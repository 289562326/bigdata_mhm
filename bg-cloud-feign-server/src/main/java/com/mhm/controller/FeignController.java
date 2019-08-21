package com.mhm.controller;

import com.mhm.services.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MaHuiming on 2019/8/21.
 */
@RestController
public class FeignController{

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(String name ){
        return name;
    }


    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2(String name ){
        return feignService.test(name);
    }
}
