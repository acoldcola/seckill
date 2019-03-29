package com.zk.seckill.controller;

import com.zk.seckill.domain.User;
import com.zk.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther ZhangKe
 * @date 2018/11/17 16:32
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/id")
    @ResponseBody
    public User getUserById() {
        User user = userService.getUserById(2);
        return user;
    }
}
