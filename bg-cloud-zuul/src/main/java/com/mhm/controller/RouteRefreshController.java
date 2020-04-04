package com.mhm.controller;

import com.mhm.entity.ResultCode;
import com.mhm.entity.ResultCodeEnum;
import com.mhm.service.RouteRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手动刷新路由
 * Created by MHm on 2020-4-4.
 */
@RestController
public class RouteRefreshController {
    @Autowired
    private RouteRefreshService routeRefreshService;
    @PostMapping("/refreshRoute")
    public ResultCode refreshRoute() {
        ResultCode result = new ResultCode();
        try {
            routeRefreshService.refreshRoute();
            result.setData("刷新成功");
        } catch (Exception e) {
            result.setCode(ResultCodeEnum.SYSTEMERROR.getCode(), "内部错误");
        }
        return result;
    }

}
