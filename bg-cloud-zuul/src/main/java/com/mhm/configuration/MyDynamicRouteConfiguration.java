package com.mhm.configuration;

import com.mhm.gateway.MyDynamicRouteLocator;
import com.mhm.dao.RouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 方式2：动态路由配置
 * Created by MHm on 2019/6/14.
 */
@Configuration
public class MyDynamicRouteConfiguration {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    RouteDao routeDao;

    /**
     * 注入到容器中
     * springboot2.0.3有servlet.getServletPrefix,2.1.5不支持
     * @return
     */
    @Bean
    public MyDynamicRouteLocator routeLocator() {
        MyDynamicRouteLocator routeLocator = new MyDynamicRouteLocator(server.getServlet().getServletPrefix(), this.zuulProperties);
        routeLocator.setRouteDao(routeDao);
        return routeLocator;
    }

}
