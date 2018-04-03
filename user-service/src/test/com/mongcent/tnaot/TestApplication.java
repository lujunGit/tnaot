package com.mongcent.tnaot;

import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.service.UserServiceV1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {UserApplication.class})
@ActiveProfiles(profiles = "test")
public class TestApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserServiceV1 userServiceV1;

    @Test
    public void addUserData() {
        List<User> channelList = userServiceV1.getUserRepository().findAll();
        if (channelList.size() > 100) {
            logger.info("当前测试表中数据：" + channelList.size() + "条，无需增加！");
        } else {
            User user = null;
            for (int i = 0; i < 100; i++) {
                user = new User();
                user.setName("用户"+i);
                user.setAge(i*5);
                user.setCreate_time(new Date());
                user.setUpdate_time(new Date());
                user.setAddress("广东-广州");
                user.setSex("男");
                user.setPhone(123456789);
                userServiceV1.addUser(user);
            }
            logger.info("数据添加完毕！");
        }
    }

    @Test
    public void getUserByName() {
        List<User> channelList = userServiceV1.getUserRepository().findAll();
        int randomId = cycleRandom(channelList.size());
        final String name = "评论" + randomId;
        List<User> users = userServiceV1.getUserByName(name);
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getName().equals(name)) {
                    logger.info("获取评论成功！");
                } else {
                    logger.error("获取评论异常！");
                }
            }
        } else {
            logger.error("获取评论失败！");
        }
    }

    @Test
    public void delRandomUser() {
        List<User> channelList = userServiceV1.getUserRepository().findAll();
        int randomId = cycleRandom(channelList.size());
        User channel = null;
        try {
            channel = userServiceV1.getUserRepository().findOne(randomId);
            if (channel != null) {
                userServiceV1.getUserRepository().delete(channel);
                logger.info("删除[" + channel.getName() + "]成功！");
            } else {
                logger.info("不存在评论" + randomId + "！");
            }
        } catch (Exception e) {
            logger.error("删除[" + channel.getName() + "]失败！", e);
        }
    }

    @Test
    public void modifyUser() {
        List<User> channelList = userServiceV1.getUserRepository().findAll();
        int randomId = cycleRandom(channelList.size());
        User oldUser = userServiceV1.getUserRepository().findOne(randomId);
        Integer flag = userServiceV1.updateUser(new Date(), new Date(), oldUser.getId
                ());
        if (flag == 1) {
            User newUser = userServiceV1.getUserRepository().findOne(randomId);
            logger.info("修改" + oldUser.getName() + "成功！修改前：" + oldUser.toString() + "|| 修改后：" + newUser
                    .toString());
        }
    }

    @Test
    public void getUserByUserId(){

    }

    public int cycleRandom(int initNum) {
        int randomId = new Random().nextInt(initNum) + 1;
        logger.info("随机数为：" + randomId);
        boolean existId = userServiceV1.getUserRepository().exists(randomId);
        if (existId) {
            return randomId;
        } else {
            return cycleRandom(initNum);
        }
    }
}
