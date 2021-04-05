package com.mhm.service;

import com.alibaba.fastjson.JSONObject;
import com.mhm.model.dao.RegionDao;
import com.mhm.model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MHm on 2019/6/13.
 */
@Service
public class RegionService {

    @Autowired
    private RegionDao regionMapper;
    public String getList(){
        List<Region> list = regionMapper.selectAll();
        return JSONObject.toJSONString(list);
    }

    public void batchInsert(List<Region> regionList){
        regionMapper.batchInsert(regionList);
    }

    @Transactional
    public void batchUpdate(long regionId){
        Region region = regionMapper.selectByPrimaryKey(regionId);
        System.out.println(region.getUserId());
        region.setUserId(region.getUserId()+1);
        regionMapper.updateByPrimaryKey(region);
    }

    @Transactional
    public synchronized void batchUpdateSync(long regionId){
        Region region = regionMapper.selectByPrimaryKey(regionId);
        System.out.println(region.getUserId());
        region.setUserId(region.getUserId()+1);
        regionMapper.updateByPrimaryKey(region);
    }
}
