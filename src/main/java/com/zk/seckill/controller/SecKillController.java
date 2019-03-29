package com.zk.seckill.controller;


import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillOrder;
import com.zk.seckill.domain.SecKillUser;
import com.zk.seckill.result.CodeMsg;
import com.zk.seckill.service.GoodsService;
import com.zk.seckill.service.OrderService;
import com.zk.seckill.service.SecKillService;
import com.zk.seckill.service.SecKillUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther ZhangKe
 * @date 2018/11/17 13:01
 */
@Controller
@RequestMapping("/miaosha")
public class SecKillController {

    @Autowired
    SecKillUserService secKillUserService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;


    private static final String COOKI_NAME_TOKEN = "token";

    private static Logger log = LoggerFactory.getLogger(SecKillController.class);

    @RequestMapping("/do_miaosha")
    public String toLogin(Model model, SecKillUser secKillUser,
    @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", secKillUser);
        if (secKillUser == null) {
            return "login";
        }
        // 查询秒杀商品库存
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        // 判断是否已经秒杀过了
        SecKillOrder order = orderService.getSeckillOrderByUserIdAndGoodsId(secKillUser.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        // 减少库存 下订单 写入秒杀订单
        OrderInfo orderInfo = secKillService.seckill(secKillUser, goods);
        model.addAttribute("orderInfo" ,orderInfo);
        model.addAttribute("goods" ,goods);
        return "order_detail";
    }
}