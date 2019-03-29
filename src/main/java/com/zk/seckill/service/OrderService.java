package com.zk.seckill.service;

import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillOrder;
import com.zk.seckill.domain.SecKillUser;

public interface OrderService {
    SecKillOrder getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId);

    OrderInfo createOrder(SecKillUser secKillUser, GoodsVo goodsVo);
}
