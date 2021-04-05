package com.mhm.model.dao;

import com.mhm.model.entity.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface RegionDao {
        int deleteByPrimaryKey(Long regionId);

        int insert(Region record);

        Region selectByPrimaryKey(Long regionId);

        List<Region> selectAll();

        int updateByPrimaryKey(Region record);

        void batchInsert(List<Region> regionList);
    }