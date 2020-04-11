package com.mhm;

import com.mhm.configuration.service.HDFSService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MHm on 2020-2-26.
 */
@RestController
public class HDFSController {
    @Resource
    private HDFSService hdfsService;

    @RequestMapping(method = RequestMethod.GET,value = "/hdfs")
    public String mkdir(){
        hdfsService.mkdir("hdfs");
        hdfsService.uploadFile("d:/test.txt","/hdfs/test.txt");
        return "ok";
    }
}
