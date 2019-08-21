package com.mhm.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by MaHuiming on 2019/8/21.
 */
@FeignClient(value = "test-service")
public interface FeignService {

    //服务中方法的映射路径
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String test(@RequestParam(value="name") String name);
}
