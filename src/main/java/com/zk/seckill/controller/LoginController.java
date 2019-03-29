package com.zk.seckill.controller;


import com.zk.seckill.Vo.LoginVo;
import com.zk.seckill.result.Result;
import com.zk.seckill.service.SecKillUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @auther ZhangKe
 * @date 2018/11/17 13:01
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    SecKillUserService secKillUserService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/to_login")
    public String toLogin(Model model) {
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        // 登录
        secKillUserService.login(response, loginVo);
        return Result.success(true);
    }
}