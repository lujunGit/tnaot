package com.mongcent.tnaot.message;

import com.mongcent.tnaot.api.v1.RedisController;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * 消息接受者
 */
public class Receiver {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RedisController.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        latch.countDown();
    }
}
