package com.zk.seckill.service;

import com.zk.seckill.Vo.GoodsVo;

import java.util.List;

public interface GoodsService {

    List<GoodsVo> getGoodsVo();

    GoodsVo getGoodsVoById(long goodsId);

    void reduceStock(GoodsVo goodsVo);
}
