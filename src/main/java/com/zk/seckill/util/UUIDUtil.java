package com.zk.seckill.util;

import java.util.UUID;

/**
 * @auther ZhangKe
 * @date 2018/11/22 22:48
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
