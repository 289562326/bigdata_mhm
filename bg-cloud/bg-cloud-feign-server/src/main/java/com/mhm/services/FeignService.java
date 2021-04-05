package com.mhm.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 不配合熔断
 * Created by MHm on 2019/8/21.
 */
@FeignClient(value = "bg-cloud-restclient")
public interface FeignService {

    //服务中方法的映射路径
    @RequestMapping(value = "/api/restclient",method = RequestMethod.GET)
    String index();
}
