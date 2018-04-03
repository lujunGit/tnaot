package com.mongcent.tnaot.service;

import com.mongcent.tnaot.exception.JsonResult;
import com.mongcent.tnaot.exception.RespCode;
import com.mongcent.tnaot.model.Channel;
import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.repository.ChannelRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * 在这里添加事务，最好在方法上面添加,避免全局事务影响读效率
 */
@Service
public class ChannelServiceV1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceV1(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    /**
     * 获取我的频道
     */
    public List<Channel> getMyChannelByName(String name) {
        return channelRepository.findChannel(name);
    }

    /**
     * 获取我的频道
     */
    public Channel getMyChannelById(Integer id) {
        return channelRepository.findOne(id);
    }

    /**
     * 新增频道
     * @param channel
     */
    public void addChannel(Channel channel) {
        channelRepository.save(channel);
    }

    public ChannelRepository getChannelRepository() {
        return channelRepository;
    }

    /**
     * 修改频道
     * 注意：删除了修改频道需要添加事务，新增和查询不需要
     * @param create_time
     * @param update_time
     * @param id
     * @return
     */
    @Transactional
    public Integer updateChannel(Date create_time, Date update_time, Integer id) {
        return channelRepository.updateChannel(create_time, update_time, id);
    }
}
