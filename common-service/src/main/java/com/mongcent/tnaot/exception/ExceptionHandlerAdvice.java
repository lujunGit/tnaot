package com.mongcent.tnaot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@RestController
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public JsonResult handleBaseException(HttpServletRequest req,BaseException e) {
        JsonResult jsonResult = new JsonResult(req.getRequestURI());
        RespCode respCode = RespCode.valueOf(e.getMessage());
        if(respCode != null){
            jsonResult.setResultCode(respCode.getResCode());
            jsonResult.setResultMsg(respCode.getResMsg());
        }
        logger.info("jsonResult : " + jsonResult.toString());
        return jsonResult;
    }

    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(HttpServletRequest req, Exception e) {
        JsonResult jsonResult = new JsonResult(req.getRequestURI());
        jsonResult.setResultMsg(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            jsonResult.setResultCode("404");
        } else {
            jsonResult.setResultCode("500");
        }
        return jsonResult;
    }
}
