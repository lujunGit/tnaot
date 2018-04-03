package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.model.RedisModel;
import com.mongcent.tnaot.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/redis/v1")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    private RedisService redisService;

    //一般发送消息的业务逻辑放在service层，这里做测试方便
    private StringRedisTemplate redisTemplate;

    @Autowired
    public RedisController(RedisService redisService, StringRedisTemplate redisTemplate) {
        this.redisService = redisService;
        this.redisTemplate = redisTemplate;
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
        final String topicName = "chat";
        //redisTemplate.convertAndSend(topicName, msg);
        redisTemplate.convertAndSend("email", "email send msg");
        redisTemplate.convertAndSend("order", "order send msg");
        redisTemplate.convertAndSend("notice", "notice send msg");
        return "ok";
    }
}
