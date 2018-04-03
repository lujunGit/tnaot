package com.mongcent.tnaot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonApplication {

    private static final Logger logger = LoggerFactory.getLogger(CommonApplication.class);

    public static void main(String[] args) {
        logger.info("Application is ready run...");
        SpringApplication.run(CommonApplication.class, args);
    }

}
