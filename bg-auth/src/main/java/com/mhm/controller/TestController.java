package com.mhm.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 *
 * Created by MaHuiming on 2019/6/26.
 */
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/public/{id}")
    public String getProduct(@PathVariable String id) {
        logger.info("当前用户认证信息：{}", JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication()));
        return "public id: " + id;
    }

    @GetMapping("/private/{id}")
    public String getOrder(@PathVariable String id) {
        logger.info("当前用户认证信息：{}", JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication()));
        return "private id : " + id;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        //principal在经过security拦截后，是org.springframework.security.authentication.UsernamePasswordAuthenticationToken
        //在经OAuth2拦截后，是OAuth2Authentication
        return principal;
    }

    @RequestMapping("/res/getMsg")
    public String getMsg(String msg,Principal principal) {//principal中封装了客户端（用户，也就是clientDetails，区别于Security的UserDetails，其实clientDetails中也封装了UserDetails），不是必须的参数，除非你想得到用户信息，才加上principal。
        return "Get the msg: "+msg;
    }


}
