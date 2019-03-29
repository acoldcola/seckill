package com.zk.seckill.util;

import com.alibaba.druid.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther ZhangKe
 * @date 2018/11/22 12:24
 */
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
