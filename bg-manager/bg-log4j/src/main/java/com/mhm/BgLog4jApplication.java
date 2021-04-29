package com.mhm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgLog4jApplication {
    private final static Logger logger = LoggerFactory.getLogger(BgLog4jApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BgLog4jApplication.class, args);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }

}
