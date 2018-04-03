package com.mongcent.tnaot;

import com.mongcent.tnaot.model.Channel;
import com.mongcent.tnaot.service.ChannelServiceV1;

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
@SpringBootTest(classes = {ChannelApplication.class})
@ActiveProfiles(profiles = "test")
public class TestApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelServiceV1 channelServiceV1;

    @Test
    public void addChannelData() {
        List<Channel> channelList = channelServiceV1.getChannelRepository().findAll();
        if (channelList.size() > 100) {
            logger.info("当前测试表中数据：" + channelList.size() + "条，无需增加！");
        } else {
            Channel channel = null;
            for (int i = 0; i < 100; i++) {
                channel = new Channel();
                channel.setName("频道" + i);
                channel.setParent_id(i);
                channel.setSort_order(1);
                channel.setIs_enabled(Byte.valueOf("0"));
                channel.setIs_default(Byte.valueOf("0"));
                channel.setImage_url("http://www/baidu.com");
                channel.setIs_video_area(Byte.valueOf("0"));
                channel.setDescription("描述");
                channel.setCreate_time(new Date());
                channel.setCreate_user("大军" + i);
                channel.setIs_fixed(Byte.valueOf("0"));
                channel.setUpdate_time(new Date());
                channel.setRemark("this is pingdao!");
                channelServiceV1.addChannel(channel);
            }
            logger.info("数据添加完毕！");
        }
    }

    @Test
    public void getChannelByName() {
        List<Channel> channelList = channelServiceV1.getChannelRepository().findAll();
        int randomId = cycleRandom(channelList.size());
        final String name = "频道" + randomId;
        List<Channel> channels = channelServiceV1.getMyChannelByName(name);
        if (channels.size() > 0) {
            for (Channel channel : channels) {
                if (channel.getName().equals(name)) {
                    logger.info("获取频道成功！");
                } else {
                    logger.error("获取频道异常！");
                }
            }
        } else {
            logger.error("获取频道失败！");
        }
    }

    @Test
    public void delRandomChannel() {
        List<Channel> channelList = channelServiceV1.getChannelRepository().findAll();
        int randomId = cycleRandom(channelList.size());
        Channel channel = null;
        try {
            channel = channelServiceV1.getChannelRepository().findOne(randomId);
            if (channel != null) {
                channelServiceV1.getChannelRepository().delete(channel);
                logger.info("删除[" + channel.getName() + "]成功！");
            } else {
                logger.info("不存在频道" + randomId + "！");
            }
        } catch (Exception e) {
            logger.error("删除[" + channel.getName() + "]失败！", e);
        }
    }

    @Test
    public void modifyChannel() {
        List<Channel> channelList = channelServiceV1.getChannelRepository().findAll();
        Integer randomId = cycleRandom(channelList.size());
        Channel oldChannel = channelServiceV1.getChannelRepository().findOne(randomId);
        Integer flag = channelServiceV1.updateChannel(new Date(), new Date(), oldChannel.getId
                ());
        if (flag == 1) {
            Channel newChannel = channelServiceV1.getChannelRepository().findOne(randomId);
            logger.info("修改" + oldChannel.getName() + "成功！修改前：" + oldChannel.toString() + "|| 修改后：" + newChannel
                    .toString());
        }
    }

    public int cycleRandom(int initNum) {
        Integer randomId = new Random().nextInt(initNum) + 1;
        logger.info("随机数为：" + randomId);
        boolean existId = channelServiceV1.getChannelRepository().exists(randomId);
        if (existId) {
            return randomId;
        } else {
            return cycleRandom(initNum);
        }
    }
}
