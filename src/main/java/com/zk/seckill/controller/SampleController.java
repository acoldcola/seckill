package com.zk.seckill.controller;

import com.zk.seckill.redis.RedisService;
import com.zk.seckill.redis.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther ZhangKe
 * @date 2018/11/17 13:01
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/hello")
    public String sample(Model model) {
        model.addAttribute("name","hello word");
        return "hello";
    }
    @RequestMapping("/hi")
    @ResponseBody
    public String mus() {
        return "hhaha";
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String redisSet() {
        boolean b = redisService.set(UserKey.getById,"key1", 1);
        Integer key1 = redisService.get(UserKey.getById,"key1", Integer.class);
        return ""+key1 + b;
    }
}