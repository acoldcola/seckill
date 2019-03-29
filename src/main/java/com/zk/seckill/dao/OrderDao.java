package com.zk.seckill.dao;

import com.zk.seckill.domain.OrderInfo;
import com.zk.seckill.domain.SecKillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @auther ZhangKe
 * @date 2018/11/24 13:47
 */
@Mapper
public interface OrderDao {
    @Select("select * from seckill_order where user_id = #{userId} and goods_id = #{goodsId}")
    SecKillOrder getSeckillOrderByUserIdAndGoodsId(@Param("userId") long userId, @Param("goodsId")long goodsId);
    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertSecKillOrder(SecKillOrder secKillOrder);
}
