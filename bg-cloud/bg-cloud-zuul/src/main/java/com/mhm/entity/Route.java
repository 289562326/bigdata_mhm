package com.mhm.entity;

import lombok.Data;

/**
 * 路由实体
 * Created by MHm on 2020-4-4.
 */
@Data
public class Route {
    private Long routeId;

    private String serviceId;

    private String url;

    private String stripPrefix;

    private Integer retryAble;

    private String apiName;

    private Integer enabled;

    private String path;
}
