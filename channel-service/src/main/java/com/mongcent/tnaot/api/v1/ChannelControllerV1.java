package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.exception.JsonResult;
import com.mongcent.tnaot.exception.RespCode;
import com.mongcent.tnaot.model.Channel;
import com.mongcent.tnaot.service.ChannelServiceV1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/channel/v1")
@ResponseBody
public class ChannelControllerV1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * springBoot规定无参构造方法必须要要
     */
    public ChannelControllerV1() {
    }

    private ChannelServiceV1 channelServiceV1;

    @Autowired
    public ChannelControllerV1(ChannelServiceV1 channelServiceV1) {
        this.channelServiceV1 = channelServiceV1;
    }

    /**
     * 获取我的频道
     */
    @GetMapping("/getMyChannelByName/{name}")
    public JsonResult getMyChannelByName(HttpServletRequest request, @PathVariable String name) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(channelServiceV1.getMyChannelByName(name));
        return jsonResult;
    }

    /**
     * 获取我的频道
     */
    @GetMapping("/getMyChannelById/{id}")
    public JsonResult getRandomChannel(HttpServletRequest request, @PathVariable Integer id) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(channelServiceV1.getMyChannelById(id));
        return jsonResult;
    }
}
