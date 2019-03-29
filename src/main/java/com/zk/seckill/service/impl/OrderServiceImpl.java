package com.zk.seckill.service.impl;

import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.dao.OrderDao;
import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillOrder;
import com.zk.seckill.domain.SecKillUser;
import com.zk.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @auther ZhangKe
 * @date 2018/11/24 13:53
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Override
    public SecKillOrder getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId) {
        return orderDao.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
    }

    @Override
    @Transactional
    public OrderInfo createOrder(SecKillUser secKillUser, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(secKillUser.getId());
        long orderId = orderDao.insert(orderInfo);
        SecKillOrder secKillOrder = new SecKillOrder();
        secKillOrder.setGoodsId(goodsVo.getId());
        secKillOrder.setOrderId(orderId);
        secKillOrder.setUserId(secKillOrder.getId());
        orderDao.insertSecKillOrder(secKillOrder);
        return orderInfo;
    }
}
