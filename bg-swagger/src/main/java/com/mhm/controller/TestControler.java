package com.mhm.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaHuiming on 2019/6/12.
 */
@Api(tags = "debug")
@RestController
@RequestMapping(value = "/debug")
public class TestControler {

    @ApiOperation(value = "debug")
    @RequestMapping()
    public String test(@RequestParam(value="name") String name) {
        //从请求头中取出token
        return "hello:"+name;
    }

    @ApiOperation(value = "debug")
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String debug(HttpServletRequest request) {
        //从请求头中取出token
        String token = request.getParameter("access_token");
        RestTemplate restTemplate = new RestTemplate();
        String rest = restTemplate.getForObject("http://122.112.200.204:18000/ycloud-oauth/oauth/authorize?response_type=token&scope=&client_id=yyc_ycloud-admin&redirect_uri=http://localhost:8080/debug/login/#/&state=e5dae6fd-cf6e-418b-9de7-466fa6f0e562",String.class);
        return "redirect:"+rest;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        //从请求头中取出token
        String token = request.getParameter("access_token");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Authorization"," Bearer "+token);
        HttpHeaders header = new HttpHeaders();
        // 需求需要传参为form-data格式
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, header);
        String url = "http://122.112.200.204:18000/ycloud-oauth/users/info";
        JSONObject response = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        response.put("userId",response.get("id"));
        response.put("userName",response.get("username"));
        //设置返回值
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("userId", response.get("id") + "");
        retMap.put("userName", response.get("username") + "");
        retMap.put("userType", response.get("accountType") + "");
        return JSONObject.toJSONString(retMap);
    }
}
