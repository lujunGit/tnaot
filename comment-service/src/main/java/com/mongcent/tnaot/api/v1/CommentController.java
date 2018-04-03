package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.exception.JsonResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment/v1")
@ResponseBody
public class CommentController {

    public CommentController() {
    }

    public JsonResult getCommentById() {
        return null;
    }


}
