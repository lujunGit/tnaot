package com.mongcent.tnaot.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@EnableScheduling  //开启调度
@Component
public class MessageSend {

    private static final Logger logger = LoggerFactory.getLogger(MessageSend.class);

    private StringRedisTemplate redisTemplate;
    private CountDownLatch countDownLatch;

    @Autowired
    public MessageSend(StringRedisTemplate stringRedisTemplate, CountDownLatch countDownLatch) {
        this.redisTemplate = stringRedisTemplate;
        this.countDownLatch = countDownLatch;
    }

    @Scheduled(fixedRate = 10000) //每两秒执行一次
    public void sendMessage() {
        final String topicName = "chat";
        Object msg = "随机消息" + new Random().nextInt(100) + 1;
        logger.info("send message to topic [" + topicName + "],msg: " + msg);
        redisTemplate.convertAndSend(topicName, msg);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("消息发送出现异常！topic: " + "[" + topicName + "],msg: " + msg);
        }
    }
}
