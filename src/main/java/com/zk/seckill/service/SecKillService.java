package com.zk.seckill.service;

import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillUser;

/**
 * @auther ZhangKe
 * @date 2018/11/24 14:07
 */
public interface SecKillService {
    OrderInfo seckill(SecKillUser secKillUser, GoodsVo goodsVo);
}
