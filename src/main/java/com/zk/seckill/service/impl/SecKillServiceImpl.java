package com.zk.seckill.service.impl;

import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillUser;
import com.zk.seckill.service.GoodsService;
import com.zk.seckill.service.OrderService;
import com.zk.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther ZhangKe
 * @date 2018/11/24 14:10
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @Override
    @Transactional
    public OrderInfo seckill(SecKillUser secKillUser, GoodsVo goodsVo) {
        // 减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goodsVo);
        return orderService.createOrder(secKillUser, goodsVo);
    }
}
