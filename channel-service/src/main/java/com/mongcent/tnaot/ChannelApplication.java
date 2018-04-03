package com.mongcent.tnaot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class ChannelApplication {

    private static final Logger logger = LoggerFactory.getLogger(ChannelApplication.class);

    public static void main(String[] args) {
        logger.info("Channel Service Running...");
        SpringApplication.run(ChannelApplication.class, args);
    }

}
