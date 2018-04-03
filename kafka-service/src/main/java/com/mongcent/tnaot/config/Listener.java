package com.mongcent.tnaot.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * 监听类，用户需要自定义
 */
public class Listener {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在此配置topic名称，可以从配置文件中获取，可以允许同时监听多个topic
     * @param record
     */
    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }
}
