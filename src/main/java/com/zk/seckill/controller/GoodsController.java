package com.zk.seckill.controller;


import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.domain.SecKillUser;
import com.zk.seckill.service.GoodsService;
import com.zk.seckill.service.SecKillUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @auther ZhangKe
 * @date 2018/11/17 13:01
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    SecKillUserService secKillUserService;
    @Autowired
    GoodsService goodsService;

    private static final String COOKI_NAME_TOKEN = "token";

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping("/to_list")
    public String toLogin(Model model, SecKillUser secKillUser) {
        model.addAttribute("user", secKillUser);
        // 查询商品列表
        List<GoodsVo> goodsList = goodsService.getGoodsVo();
        model.addAttribute("goodsList" ,goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, SecKillUser secKillUser,
    @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", secKillUser);
        // 查询商品详情
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goods" ,goods);
        // 判断秒杀是否开始
        long startAT = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAT) {// 秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAT-now)/1000);
        }else if (now >endAt) {// 秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {// 秒杀正在进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}