package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.model.RedisModel;
import com.mongcent.tnaot.service.RedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    //一般发送消息的业务逻辑放在service层，这里做测试方便
    private RedisTemplate redisTemplate;

    @Autowired
    public RedisController(RedisService redisService, RedisTemplate redisTemplate) {
        this.redisService = redisService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 测试放入缓存对象以及设置过期时间 过期时间有问题
     */
    @GetMapping("/users/{id}")
    @Cacheable(value = "users", key = "#id")
    public Object getRedisModelById(@PathVariable String id) {
        logger.info("没有获取到redis值！");
        long expire = new Random().nextInt(20) + 1;
        logger.info("缓存key【" + id + "】的有效时间为：" + expire);
        //增加过期时间
        redisService.expire(id, expire);
        RedisModel model = new RedisModel();
        model.setAddress("广东广州");
        model.setAge(new Random().nextInt(100) + 1);
        model.setId(UUID.randomUUID().hashCode());
        model.setName("大军");
        model.setSex("男");
        return model;
    }

    /**
     * 测试删除指定缓存对象
     */
    @GetMapping("/clearCache/{id}")
    @CacheEvict(value = "users", key = "#id")
    public void clearCache(@PathVariable String id) {
        logger.info("缓存key【" + id + "】已经被清除！");
    }

    /**
     * 测试更新缓存对象
     */
    @GetMapping("/updateCache/{id}")
    @CachePut(value = "users", key = "#id")
    public Object updateCache(@PathVariable String id) {
        Object value = new Random().nextInt(1000) + 1;
        logger.info("更新缓存key【" + id + "】" + value);
        return value;
    }

    /**
     * 测试异步发送消息
     */
    @GetMapping("/sandSyncMsg/{msg}")
    public Object sandSyncMsg(@PathVariable String msg) {
        final String topicName = "chat";
        //redisTemplate.convertAndSend(topicName, msg);
        redisTemplate.convertAndSend("email", "email send msg");
        redisTemplate.convertAndSend("order", "order send msg");
        redisTemplate.convertAndSend("notice", "notice send msg");
        return "ok";
    }

    /**
     * 测试String类型的对象过期时间 过期时间有问题
     */
    @GetMapping("/testExpire/{id}")
    public Object testExpire(@PathVariable String id) {
        if (null != redisService.get(id)) {
            logger.info("0缓存key【" + id + "】剩余有效时间为：" + redisService.getExpireTime(id));
            return redisService.get(id);
        } else {
            String value = "随机数" + new Random().nextInt(100) + 1;
            long expire = new Random().nextInt(50) + 1;
            redisService.set(id, value, expire);
            logger.info("缓存key【" + id + "】设置的有效时间为：" + expire);
            logger.info("缓存key【" + id + "】存在状态" + redisService.existKey(id));
            return value;
        }
    }
}
