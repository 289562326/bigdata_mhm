package com.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by MaHuiming on 2019/6/26.
 */
@Component
public class Sch {
    @Autowired
    private ContextRefresher contextRefresher;
    @Scheduled(fixedRate = 5000, initialDelay = 3*1000)
    public void refresh(){
        Set<String> result = contextRefresher.refresh();
        System.err.println(result);
    }
}
