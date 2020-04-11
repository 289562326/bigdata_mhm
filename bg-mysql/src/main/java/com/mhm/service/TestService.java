package com.mhm.service;

import com.alibaba.fastjson.JSONObject;
import com.mhm.model.dao.RegionDao;
import com.mhm.model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MHm on 2019/6/13.
 */
@Service
public class TestService {

    @Autowired
    private RegionDao regionMapper;
    public String getList(){
        List<Region> list = regionMapper.selectAll();
        return JSONObject.toJSONString(list);
    }
}
