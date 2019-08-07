package com.mhm.configuration;

import com.mhm.gateway.CustomRouteLocator;
import com.mhm.gateway.RouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 网关配置
 * Created by MaHuiming on 2019/6/14.
 */
@Configuration
public class GatewayConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    RouteDao routeDao;

    /**
     * springboot2.0.3有servlet.getServletPrefix,2.1.5不支持
     * @return
     */
    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(server.getServlet().getServletPrefix(), this.zuulProperties);
        routeLocator.setRouteDao(routeDao);
        return routeLocator;
    }

}
