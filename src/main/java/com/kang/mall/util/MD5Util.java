package com.kang.mall.util;

import com.kang.mall.common.Constants;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author yikang
 * ClassName: MD5Util
 * Description:
 * Create Date: 2021/1/15 20:37
 */
public class MD5Util {

    public static String MD5Encode(String username, String password) {
        String hashUsername = md5(username);
        String salt = md5(hashUsername.substring(0, Constants.MD5_SUBSTRING_LENGTH));

        return md5(password + salt);
    }

    private static String md5(String originString) {
        return DigestUtils.md5DigestAsHex(originString.getBytes(StandardCharsets.UTF_8));
    }
}
