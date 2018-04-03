package com.mongcent.tnaot.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 此接口主要是做单独一个服务的多个接口实现访问
 * 使用Feign方式
 */
@FeignClient(value= "user-service")
public interface CommentServiceV2 {

    @GetMapping("/getUserById/{id}")
    Object getUserByCommentId(@PathVariable Integer id);
}
