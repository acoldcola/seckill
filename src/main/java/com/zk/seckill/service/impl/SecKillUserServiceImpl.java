package com.zk.seckill.service.impl;

import com.zk.seckill.Vo.LoginVo;
import com.zk.seckill.dao.SecKillUserDao;
import com.zk.seckill.domain.SecKillUser;
import com.zk.seckill.exception.GlobalException;
import com.zk.seckill.redis.RedisService;
import com.zk.seckill.redis.SecKillUserKey;
import com.zk.seckill.result.CodeMsg;
import com.zk.seckill.service.SecKillUserService;
import com.zk.seckill.util.MD5Util;
import com.zk.seckill.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther ZhangKe
 * @date 2018/11/22 13:39
 */
@Service
public class SecKillUserServiceImpl implements SecKillUserService {

    private static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    SecKillUserDao secKillUserDao;
    @Autowired
    RedisService redisService;

    @Override
    public SecKillUser getById(Long id) {
        return secKillUserDao.getById(id);
    }

    @Override
    public boolean  login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        // 判断手机是否重复
        SecKillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPassword = user.getPassword();
        String saltDB = user.getSalt();
        String cPassword = MD5Util.formPassToDBPass(password, saltDB);
        if (!cPassword.equals(dbPassword)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生产token
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    @Override
    public SecKillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SecKillUser secKillUser = redisService.get(SecKillUserKey.token, token, SecKillUser.class);
        // 延长有效期
        if (secKillUser != null) {
            addCookie(response, token, secKillUser);
        }
        return secKillUser;
    }

    // 生成cookie
    public void addCookie(HttpServletResponse response, String token, SecKillUser secKillUser) {
        redisService.set(SecKillUserKey.token, token, secKillUser);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(SecKillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
