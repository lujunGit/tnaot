package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.message.MessageSend;
import com.mongcent.tnaot.model.RedisModel;
import com.mongcent.tnaot.service.RedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/redis/v1")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    private RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/getRedisModelById/{id}")
    @Cacheable(value = "getRedisModelById")
    public Object getRedisModelById(@PathVariable Integer id) {
        logger.info("没有获取到redis值！");
        RedisModel model = new RedisModel();
        model.setAddress("广东广州");
        model.setAge(new Random().nextInt(100) + 1);
        model.setId(UUID.randomUUID().hashCode());
        model.setName("大军");
        model.setSex("男");
        return model;
    }

    @GetMapping("/sandSyncMsg/{msg}")
    public Object sandSyncMsg(@PathVariable String msg) {
        MessageSend messageSend = new MessageSend();
        final String topicName = "chat";
        messageSend.sendMessage(topicName, msg);
        return "ok";
    }
}
