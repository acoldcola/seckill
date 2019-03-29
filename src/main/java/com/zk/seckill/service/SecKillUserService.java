package com.zk.seckill.service;

import com.zk.seckill.Vo.LoginVo;
import com.zk.seckill.domain.SecKillUser;

import javax.servlet.http.HttpServletResponse;

public interface SecKillUserService {
    SecKillUser getById(Long id);

    boolean login(HttpServletResponse response, LoginVo loginVo);

    SecKillUser getByToken(HttpServletResponse response, String token);
}
