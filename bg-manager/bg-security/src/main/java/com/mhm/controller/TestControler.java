package com.mhm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhm.utils.SSLUtil;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHm on 2019/6/12.
 */
@Api(tags = "debug")
@RestController
@RequestMapping(value = "/debug")
public class TestControler {
    private static final String FETCH_PROJECT_FLOWS = "{0}/manager?ajax=fetchprojectflows&session.id={1}&project={2}";

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

    @RequestMapping(method = RequestMethod.GET, value = "/azkaban")
    public String azkaban(HttpServletRequest request) {
        //从请求头中取出token
        RestTemplate restTemplate = new RestTemplate();

        try {
            SSLUtil.turnOffSslChecking();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("X-Requested-With", "XMLHttpRequest");
        LinkedMultiValueMap<String,String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
        linkedMultiValueMap.add("action", "login");
        linkedMultiValueMap.add("username", "azkaban");
        linkedMultiValueMap.add("password", "azkaban");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(linkedMultiValueMap, headers);


        String rest = restTemplate.postForObject("http://192.168.0.197:8081",httpEntity,String.class);
        JSONObject jsonObject = JSON.parseObject(rest);
        String sessionId = jsonObject.getString("session.id");
        String projectName = null;
        String projects = restTemplate.getForObject("http://192.168.0.197:8081/index?all&session.id="+sessionId,String.class);

        return projects;
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
