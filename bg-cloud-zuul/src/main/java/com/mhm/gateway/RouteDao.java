package com.mhm.gateway;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 路由查询接口
 * Created by MaHuiming on 2019/6/14.
 */
@Repository
public interface RouteDao {

    List<Route> selectAll();

}