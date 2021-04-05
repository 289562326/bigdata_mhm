package com.mhm.services;

import com.mhm.component.FeignApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调用Hystrix的微服务，用于验证熔断器
 * Created by MHm on 2019/8/21.
 */
@FeignClient(value = "bg-cloud-zipkin",fallback = FeignApiFallBack.class)
public interface HystrixService {

    //服务中方法的映射路径
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String test();
}
