package com.mhm.controller;

import com.alibaba.fastjson.JSONObject;
import com.mhm.model.entity.Region;
import com.mhm.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据库压力测试
 *
 * @author MHm
 * @date 2020-5-16 13:35
 */
@RestController
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/batchInsert", method = RequestMethod.POST)
    public JSONObject batchInsert(@RequestParam("insertNum") Integer insertNum,
    @RequestParam("threadNum") Integer threadNum) {
        List<Region> regionList = new ArrayList<Region>();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-05-18 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < insertNum; i++) {
            Region region = new Region();
            region.setRegionId((long)i);
            region.setRegionName("name" + i);
            region.setUpRegionId(i);
            region.setCreateTime(date);
            region.setModelId(1);
            region.setPath("/test/test01");
            region.setProjectId(1);
            region.setRegionLevel((byte) 1);
            region.setRemark("压力测试用");
            region.setSort((short) 1);
            region.setState((byte) 1);
            region.setUpdateTime(date);
            region.setUserId(101);
            region.setUserName("admin");
            regionList.add(region);
        }

        ThreadPoolExecutor localThreadPoolExecutor = new ThreadPoolExecutor(threadNum, threadNum, 30L, TimeUnit.SECONDS,
        new LinkedBlockingQueue());
        long startTime = System.currentTimeMillis();
        for(int i=0;i<threadNum;i++){
            localThreadPoolExecutor.execute(
            new Thread() {
                public void run() {
                    regionService.batchInsert(regionList);
                }
            });
        }
        localThreadPoolExecutor.shutdown();
        // 等待线程都处理完
        try {
            boolean bool = false;
            while (!(bool)) {
                bool = localThreadPoolExecutor.awaitTermination(-6037246970332971007L, TimeUnit.SECONDS);
            }
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        result.put("time", endTime - startTime);

        return result;
    }

    /**
     * 多线程测试批量更新的事务问题
     * @param insertNum
     * @param threadNum
     * @return
     */
    @RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
    public void batchUpdate(@RequestParam("insertNum") Integer insertNum,
    @RequestParam("threadNum") Integer threadNum) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                regionService.batchUpdate(74);
            }).start();
        }

    }
}
