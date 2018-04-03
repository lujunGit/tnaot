package com.mongcent.tnaot.controller;

import com.mongcent.tnaot.exception.BaseException;
import com.mongcent.tnaot.exception.JsonResult;
import com.mongcent.tnaot.exception.RespCode;
import com.mongcent.tnaot.model.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试、实例代码
 */
@RestController
@RequestMapping("/v1")
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @RequestMapping("/index/{name}")
    @ResponseBody
    public JsonResult index(HttpServletRequest request, @PathVariable String name){
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        if(StringUtils.isEmpty(name)) {
            logger.info("log is info lever");
            throw new BaseException(RespCode.PARAMETER_ERROR);//这种方式写在实现层
        }
        if(name.equals("aa")){
            logger.debug("log is debug lever");
            throw new BaseException(RespCode.REQUEST_ERROR);
        }
        if(name.equals("bb")){
            logger.warn("log is warn lever");
            throw new BaseException(RespCode.NULL_POINT);
        }
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(TestEntity.getUsers());
        return jsonResult;
    }

    @RequestMapping("/version")
    public String getProjectVersion(){
        if(env != null){
            String description = env.getProperty("spring.application.description");
            String version = env.getProperty("spring.application.version");
            System.out.print(description + version);
            return version;
        }
        return null;
    }

    @RequestMapping("/home")
    public String home(){
        try {
            throw new Exception("sss");
        }catch(Exception e){
            System.out.print("print Exception!");
        }
        return "index";
    }


}
