package com.zk.seckill.dao;

import com.zk.seckill.Vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @auther ZhangKe
 * @date 2018/11/22 13:30
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*,s.miaosha_price,s.stock_count,s.start_date,s.end_date from seckill_goods s left join goods g on s.goods_id = g.id")
    List<GoodsVo> getGoodsVo();
    @Select("select g.*,s.miaosha_price,s.stock_count,s.start_date,s.end_date from seckill_goods s left join goods g on s.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoById(@Param("goodsId")long goodsId);
    @Update("update seckill_goods set stock_count = stock_count-1 where goods_id = #{id}")
    void reduceStock(long id);
}
