package com.mhm.component;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.CompositeRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.List;
import java.util.Map;

/**
 * 直接更新路由信息
 * Created by MHm on 2020-4-4.
 */
//暂时注释掉
//@Component
public class DynamicRoutes2Processor implements InitializingBean {

    @Autowired
    private CompositeRouteLocator compositeRouteLocator;
    @Autowired
    private ZuulProperties zuulProperties;

    /**
     * 动态加入路由
     *
     * @param routeList 路由信息
     */
    public void refreshRoutes(List<ZuulProperties.ZuulRoute> routeList) {
        Map<String, ZuulProperties.ZuulRoute> routes = zuulProperties.getRoutes();

        for (ZuulProperties.ZuulRoute route : routeList) {
            routes.put(route.getId(), route);
        }
        compositeRouteLocator.refresh();
    }

    /**
     * 初始化路由信息, 可以加载任意数据源
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, ZuulProperties.ZuulRoute> routes = zuulProperties.getRoutes();
        //可以从数据库、redis或者其他方式获取路由信息
        routes.put("bg-cloud-restclient", new ZuulProperties.ZuulRoute("/api/restclient/**", "bg-restclient"));
        compositeRouteLocator.refresh();
    }
}
