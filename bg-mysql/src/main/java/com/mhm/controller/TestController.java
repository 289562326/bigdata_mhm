package com.mhm.controller;

import com.mhm.service.TableService;
import com.mhm.service.TestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MHm on 2019/6/13.
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {
    @Resource
    private TestService testService;
    @Resource
    private TableService tableService;

//    @RequestMapping(value = "/sql",method = RequestMethod.GET)
//    public String test(){
//        String jsonObject = testService.getList();
//        return jsonObject;
//    }

    /**
     * 根据主键查询数据
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/{tableName}/{key}",method = RequestMethod.GET)
    public String findOne(@PathVariable(name="tableName")String tableName,@PathVariable(name="key")String key){
        String jsonObject = tableService.getListByKey(tableName,key);
        return jsonObject;
    }

    /**
     * 查询表里面所有的数据
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/{tableName}",method = RequestMethod.GET)
    public String selectAll(@PathVariable(name="tableName")String tableName){
        String jsonObject = tableService.getList(tableName);
        return jsonObject;
    }

    /**
     * 更新数据
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/{tableName}/{key}",method = RequestMethod.PUT)
    public String update(@PathVariable(name="tableName")String tableName,@PathVariable(name="key")String key){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("region_id", key);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("region_name", "'test2'");
        tableService.updateByPrimaryKey(tableName,key,params,values);
        return "success";
    }


    /**
     * 新增数据
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/{tableName}/{key}",method = RequestMethod.POST)
    public String insert(@PathVariable(name="tableName")String tableName,@PathVariable(name="key")String key){
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("region_name", "'test'");
        List<String> columns = new ArrayList<String>();
        columns.add("region_name");
        tableService.insert(tableName,key,values,columns);
        return "success";
    }

    /**
     * 删除数据
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/{tableName}/{key}",method = RequestMethod.DELETE)
    public String delete(@PathVariable(name="tableName")String tableName,@PathVariable(name="key")String key){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("region_id", key);
        tableService.deleteByPrimaryKey(tableName,key,params);
        return "success";
    }
}
