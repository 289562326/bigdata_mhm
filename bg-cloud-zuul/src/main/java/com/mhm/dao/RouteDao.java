package com.mhm.dao;

import com.mhm.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 路由查询接口
 * Created by MHm on 2019/6/14.
 */
@Repository
public interface RouteDao {

    List<Route> selectAll();

}