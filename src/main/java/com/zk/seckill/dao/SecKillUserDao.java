package com.zk.seckill.dao;

import com.zk.seckill.domain.SecKillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @auther ZhangKe
 * @date 2018/11/22 13:30
 */
@Mapper
public interface SecKillUserDao {

    @Select("select * from seckill_user where id = #{id}")
    SecKillUser getById(@Param("id")Long id);
}
