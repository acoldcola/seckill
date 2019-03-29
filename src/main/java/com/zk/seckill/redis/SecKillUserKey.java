package com.zk.seckill.redis;

/**
 * @auther ZhangKe
 * @date 2018/11/22 22:52
 */
public class SecKillUserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600 * 24 *2;

    public SecKillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static SecKillUserKey token = new SecKillUserKey(TOKEN_EXPIRE,"tk");
}
