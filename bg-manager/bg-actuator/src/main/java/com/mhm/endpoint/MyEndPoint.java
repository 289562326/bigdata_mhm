package com.mhm.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHm on 2019/9/2.
 */
@Endpoint(id = "mhm")
public class MyEndPoint {
    @ReadOperation
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("author", "MHm");
        result.put("age", "33");
        result.put("email", "test@qq.com");
        return result;
    }
}
