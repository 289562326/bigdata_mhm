package com.mhm.component;

import com.mhm.services.HystrixService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 熔断器
 * Created by MHm on 2019/8/30.
 */
@Component
public class FeignApiFallBack implements HystrixService{
    @RequestMapping("/test")
    public String test(){
        return "failed";
    }
}
