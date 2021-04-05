package com.mhm.controller;

import com.mhm.services.FeignService;
import com.mhm.services.HystrixService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by MHm on 2019/8/21.
 */
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping(value = "/api/name", method = RequestMethod.POST)
    public String test(String name) {
        return name;
    }

    @RequestMapping(value = "/api/httpclient", method = RequestMethod.GET)
    public String httpclient(String name) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8764/api/name";
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = null;

            entity = new StringEntity("param");

            entity.setContentType("application/json");
            entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("token","123");
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status Code : " + statusCode);
            if (statusCode != HttpStatus.SC_NO_CONTENT) {
                System.out.println("Request failed! " + response.getStatusLine());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    @RequestMapping(value = "/api/restclient", method = RequestMethod.GET)
    public String index(String name) {

        return feignService.index();
    }

    @RequestMapping(value = "/api/hystrix", method = RequestMethod.GET)
    public String test3() {
        return hystrixService.test();
    }

}
