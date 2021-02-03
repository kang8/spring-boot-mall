package com.kang.mall.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author yikang
 * ClassName: MD5Utils
 * Description: md5 工具类
 * Create Date: 2021/1/15 20:37
 */
public class MD5Utils {

    public static String customizeMd5Encode(String username, String password) {
        String hashUsername = md5(username);
        char usernameFirstChar = username.charAt(0);
        String salt = md5(hashUsername.substring(0, usernameFirstChar & 31));

        return md5(password + salt);
    }

    private static String md5(String originString) {
        return DigestUtils.md5DigestAsHex(originString.getBytes(StandardCharsets.UTF_8));
    }
}
