package com.mongcent.tnaot.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

public class MessageSend {

    private static final Logger logger = LoggerFactory.getLogger(MessageSend.class);

    private StringRedisTemplate stringRedisTemplate;
    private CountDownLatch countDownLatch;

    @Autowired
    public MessageSend(StringRedisTemplate stringRedisTemplate, CountDownLatch countDownLatch) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.countDownLatch = countDownLatch;
    }

    public MessageSend() {
    }

    public void sendMessage(String topicName, Object msg) {
        logger.info("send message to topic [" + topicName + "],msg: " + msg);
        stringRedisTemplate.convertAndSend(topicName, msg);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("消息发送出现异常！topic: " + "[" + topicName + "],msg: " + msg);
        }

    }
}
