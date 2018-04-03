package com.mongcent.tnaot.service;

import com.mongcent.tnaot.model.Comment;
import com.mongcent.tnaot.repository.CommentRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class CommentServiceV1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;
    private CommentRepository commentRepository;

    @Value("${spring.application.userUrl}")
    private String userUrl;

    @Autowired
    public CommentServiceV1(@LoadBalanced RestTemplate restTemplate, CommentRepository commentRepository) {
        this.restTemplate = restTemplate;
        this.commentRepository = commentRepository;
    }

    /**
     * 根据Id获取
     */
    public Comment getCommentsById(Integer id) {
        return commentRepository.getOne(id);
    }

    /**
     * 新增
     */
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 获取评论
     */
    public List<Comment> getMyCommentByText(String text) {
        return commentRepository.findComment(text);
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    /**
     * 更新评论
     */
    @Transactional
    public Integer updateComment(Date create_time, Date update_time, Integer id) {
        return commentRepository.updateComment(create_time, update_time, id);
    }

    /**
     * 获取评论作者 如果get方法中有访问远程接口也是需要添加事务，并且配置文件中开放session,否则会出现懒加载问题
     */
    @HystrixCommand(ignoreExceptions = {IllegalStateException.class}, fallbackMethod = "getUserByCommentIdError")
    @Transactional
    public Object getUserByCommentId(Integer id) {
        Comment comment = commentRepository.getOne(id);
        if (comment != null) {
            return restTemplate.getForObject(userUrl + comment.getUserId(), Object.class);
        } else {
            return null;
        }
    }

    /**
     * 在配置熔断方法时，熔断类的返回类型和入参个数必须都相同，并且添加忽略的异常，否组会直接进入到熔断方法
     * @param id
     * @return
     */
    public Object getUserByCommentIdError(Integer id) {
        logger.error("方法【getUserByCommentId】执行熔断处理，id=" + id);
        return null;
    }
}
