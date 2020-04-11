package com.mhm.configuration;

import com.mhm.configuration.service.HDFSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Created by MHm on 2019/7/5.
 */
@Configuration
public class HDFSConfiguration {

    @Value("${hdfs.path}")
    private String defaultHDFSUri;

    public HDFSService getHdfsService() {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", defaultHDFSUri);
        return new HDFSService(configuration, defaultHDFSUri);
    }

}
