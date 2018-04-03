package com.mongcent.tnaot.service;

import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class UserServiceV1 {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    public UserRepository getUserRepository(){
        return this.userRepository;
    }

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     * 更新用户
     * @param create_Time
     * @param update_time
     * @param id
     * @return
     */
    @Transactional
    public Integer updateUser(Date create_Time, Date update_time,Integer id){
        return userRepository.updateUser(create_Time, update_time, id);
    }

    /**
     * 查找用户
     * @param name
     * @return
     */
    public List<User> getUserByName(String name){
        return userRepository.findUser(name);
    }
}
