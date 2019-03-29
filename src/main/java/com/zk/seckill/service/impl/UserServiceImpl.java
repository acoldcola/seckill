package com.zk.seckill.service.impl;

import com.zk.seckill.dao.UserDao;
import com.zk.seckill.domain.User;
import com.zk.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther ZhangKe
 * @date 2018/11/17 16:30
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
