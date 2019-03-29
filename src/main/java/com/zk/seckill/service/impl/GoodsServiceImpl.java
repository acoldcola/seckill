package com.zk.seckill.service.impl;

import com.zk.seckill.Vo.GoodsVo;
import com.zk.seckill.dao.GoodsDao;
import com.zk.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ZhangKe
 * @date 2018/11/24 10:35
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<GoodsVo> getGoodsVo() {
        return goodsDao.getGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoById(long goodsId) {
        return goodsDao.getGoodsVoById(goodsId);
    }

    @Override
    public void reduceStock(GoodsVo goodsVo) {
        goodsDao.reduceStock(goodsVo.getId());
    }
}
