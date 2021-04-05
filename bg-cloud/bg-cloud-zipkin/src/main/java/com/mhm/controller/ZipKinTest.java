package com.mhm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by MHm on 2019/8/26.
 */
@RestController
public class ZipKinTest {

    @RequestMapping(value = "/test1")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/test")
    public String test(@RequestParam(value = "name")String name){
        return "test:"+name;
    }
    @RequestMapping(value = "/test2")
    public String test2(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://bg-center:8764/test",String.class).getBody();
    }
}
