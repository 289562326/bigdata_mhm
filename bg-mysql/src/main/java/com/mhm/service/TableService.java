package com.mhm.service;

import com.alibaba.fastjson.JSONObject;
import com.mhm.model.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用表查询
 * Created by MaHuiming on 2019/7/3.
 */
@Service
public class TableService {

    @Autowired
    private TableDao tableDao;

    public String getListByKey(String tableName, String key) {
        List<String> columns = new ArrayList<String>();
        columns.add("region_id");
        columns.add("region_name");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("region_id", key);

        JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("columns", columns);
        object.put("key", "region_id");
        object.put("params", params);
        List<HashMap<Object, Object>> list = tableDao.selectByPrimaryKey(object);
        return JSONObject.toJSONString(list);
    }

    public String getList(String tableName) {
        List<String> columns = new ArrayList<String>();
        columns.add("region_id");
        columns.add("region_name");

        JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("columns", columns);
        List<HashMap<Object, Object>> list = tableDao.selectAll(object);
        return JSONObject.toJSONString(list);
    }

    public int deleteByPrimaryKey(String tableName, String key, Map<String, Object> params) {
        JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("key", key);
        object.put("params", params);
        return this.tableDao.deleteByPrimaryKey(object);
    }

    public void insert(String tableName, String key, Map<String, Object> values,List<String> columns) {
        JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("key", key);
        object.put("values", values);
        object.put("columns", columns);

        this.tableDao.insert(object);
    }

    public void updateByPrimaryKey(String tableName,String key, Map<String, Object> params,Map<String, Object> values) {
        JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("key", key);
        object.put("params", params);
        this.tableDao.updateByPrimaryKey(object);
    }
}
