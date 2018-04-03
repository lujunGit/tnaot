package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.exception.JsonResult;
import com.mongcent.tnaot.exception.RespCode;
import com.mongcent.tnaot.service.UserServiceV1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ResponseBody
@RequestMapping("/user/v1")
public class UserController {

    public UserController(){}

    private UserServiceV1 userServiceV1;

    @Autowired
    public UserController(UserServiceV1 userServiceV1){
        this.userServiceV1 = userServiceV1;
    }

    /**
     * 获取用户
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    public JsonResult getUserById(HttpServletRequest request, @PathVariable Integer id){
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(userServiceV1.getUserById(id));
        return jsonResult;
    }
}
