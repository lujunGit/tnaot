package com.mongcent.tnaot;

import com.mongcent.tnaot.model.Comment;
import com.mongcent.tnaot.service.CommentServiceV1;
import com.mongcent.tnaot.service.CommentServiceV2;

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
@SpringBootTest(classes = {CommentApplication.class})
@ActiveProfiles(profiles = "test")
public class TestApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentServiceV1 commentServiceV1;
    @Autowired
    private CommentServiceV2 commentServiceV2;

    @Test
    public void addCommentData() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        if (commentList.size() > 100) {
            logger.info("当前测试表中数据：" + commentList.size() + "条，无需增加！");
        } else {
            Comment comment = null;
            for (int i = 0; i < 100; i++) {
                comment = new Comment();
                comment.setText("用户" + i);
                comment.setUserId(i);
                comment.setCreate_time(new Date());
                comment.setUpdate_time(new Date());
                commentServiceV1.addComment(comment);
            }
            logger.info("数据添加完毕！");
        }
    }

    @Test
    public void getCommentByName() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        int randomId = cycleRandom(commentList.size());
        final String name = "用户" + randomId;
        List<Comment> comments = commentServiceV1.getMyCommentByText(name);
        if (comments.size() > 0) {
            for (Comment comment : comments) {
                if (comment.getText().equals(name)) {
                    logger.info("获取用户成功！");
                } else {
                    logger.error("获取用户异常！");
                }
            }
        } else {
            logger.error("获取用户失败！");
        }
    }

    @Test
    public void delRandomComment() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        int randomId = cycleRandom(commentList.size());
        Comment comment = null;
        try {
            comment = commentServiceV1.getCommentRepository().findOne(randomId);
            if (comment != null) {
                commentServiceV1.getCommentRepository().delete(comment);
                logger.info("删除[" + comment.getText() + "]成功！");
            } else {
                logger.info("不存在用户" + randomId + "！");
            }
        } catch (Exception e) {
            logger.error("删除[" + comment.getText() + "]失败！", e);
        }
    }

    @Test
    public void modifyComment() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        int randomId = cycleRandom(commentList.size());
        Comment oldComment = commentServiceV1.getCommentRepository().findOne(randomId);
        Integer flag = commentServiceV1.updateComment(new Date(), new Date(), oldComment.getId
                ());
        if (flag == 1) {
            Comment newComment = commentServiceV1.getCommentRepository().findOne(randomId);
            logger.info("修改" + oldComment.getText() + "成功！修改前：" + oldComment.toString() + "|| 修改后：" + newComment
                    .toString());
        }
    }

    @Test
    public void getUserByCommentId() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        int randomId = cycleRandom(commentList.size());
        Object user = commentServiceV1.getUserByCommentId(randomId);
        if (null != user) {
            logger.info("用户信息：" + user.toString());
        } else {
            logger.error("获取外部接口数据出错！");
        }
    }

    public int cycleRandom(int initNum) {
        Integer randomId = new Random().nextInt(initNum) + 1;
        logger.info("随机数为：" + randomId);
        boolean existId = commentServiceV1.getCommentRepository().exists(randomId);
        if (existId) {
            return randomId;
        } else {
            return cycleRandom(initNum);
        }
    }

    /**
     * 有问题
     */
    @Test
    public void testFeignGetUser() {
        List<Comment> commentList = commentServiceV1.getCommentRepository().findAll();
        int randomId = cycleRandom(commentList.size());
        Object object = commentServiceV2.getUserByCommentId(randomId);
        if (object != null) {
            logger.info("userObj: " + object.toString());
        } else {
            logger.error("获取用户信息失败！用户编号：" + randomId);
        }
    }
}
