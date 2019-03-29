package com.zk.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @auther ZhangKe
 * @date 2018/11/22 11:30
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.print(inputPassToFormPass("123456"));
        System.out.print(";;;;;;;;;;;;;;;;;;;;;;;;");
        System.out.print(formPassToDBPass(inputPassToFormPass("123456"),"1a2b3c"));
    }
}
