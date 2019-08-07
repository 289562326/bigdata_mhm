package com.mhm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Security测试
 * Created by MaHuiming on 2019/7/1.
 */
@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String test(){
        return "test";
    }
}
