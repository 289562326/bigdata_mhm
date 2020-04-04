package com.mhm.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.CompositeRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 通过直接写PrepertySource更新路由
 * Created by MaHuiming on 2020-4-4.
 */
//暂时注释掉
//@Component
public class DynamicRoutesProcessor  implements BeanFactoryPostProcessor, EnvironmentAware, ApplicationContextAware,
PriorityOrdered {

    private static final String ZUUL_PROPERTY_SOURCE = "custom.zuul.routes";
    private ConfigurableEnvironment environment;
    private ApplicationContext applicationContext;
    private MapPropertySource routePropertySource = null;
    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private CompositeRouteLocator compositeRouteLocator;

    /**
     * 初始化路由
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
    throws BeansException {
        MutablePropertySources propertySources = environment.getPropertySources();
        // 可以从数据库、Redis、配置中心等获取路由信息
        Map<String, Object> data = new HashMap<>();
        data.put("zuul.routes.api-b.path", "/api/feign/**");
        data.put("zuul.routes.api-b.serviceId", "bg-cloud-feign");
        routePropertySource = new MapPropertySource(ZUUL_PROPERTY_SOURCE, data);
        // 设置最高优先级
        propertySources.addFirst(routePropertySource);
    }

    /**
     * 动态刷新
     * @param routeList
     */
    public void refreshRoutes(List<ZuulProperties.ZuulRoute> routeList) {
        Map<String, ZuulProperties.ZuulRoute> routes = zuulProperties.getRoutes();
        for (ZuulProperties.ZuulRoute route : routeList) {
            routes.put(route.getId(), route);
        }
        applicationContext.publishEvent(new EnvironmentChangeEvent(new HashSet<>()));
        applicationContext.publishEvent(new RoutesRefreshedEvent(compositeRouteLocator));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
