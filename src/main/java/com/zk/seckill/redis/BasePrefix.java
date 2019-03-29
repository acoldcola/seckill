package com.zk.seckill.redis;

/**
 * @auther ZhangKe
 * @date 2018/11/19 13:17
 */
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;
    public BasePrefix(String prefix) {
       this(0,prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {// 默认0是用不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
