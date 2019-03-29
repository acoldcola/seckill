package com.zk.seckill;

import com.zk.seckill.redis.SecKillUserKey;
import com.zk.seckill.util.UUIDUtil;
import org.junit.Test;

import javax.servlet.http.Cookie;

/**
 * @auther ZhangKe
 * @date 2018/11/24 16:19
 */
public class CookieTest {
    private static final String COOKI_NAME_TOKEN = "token";
    @Test
    public void fun() {
        String token = UUIDUtil.uuid();
        Cookie cookie = new Cookie("token", token);
        System.out.print(cookie.getName());
        cookie.setMaxAge(SecKillUserKey.token.expireSeconds());
        cookie.setPath("/");
        System.out.print(cookie.getName());
        System.out.print("1233");
        System.out.print(cookie.getValue());
    }

}
