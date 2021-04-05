package com.mhm.model.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MHm on 2019/7/3.
 */
//@Mapper
@Repository
public interface TableDao {
    ArrayList<HashMap<Object,Object>> selectByPrimaryKey(JSONObject dataJson);

    ArrayList<HashMap<Object,Object>> selectAll(JSONObject dataJson);

    int deleteByPrimaryKey(JSONObject dataJson);

    int insert(JSONObject dataJson);

    int updateByPrimaryKey(JSONObject dataJson);
}
