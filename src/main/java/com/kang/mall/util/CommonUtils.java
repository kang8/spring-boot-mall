package com.kang.mall.util;

import javax.servlet.http.HttpSession;

/**
 * @author yikang
 * ClassName: CommonUtils
 * Create Date: 2021/2/1 19:43
 */
public class CommonUtils {

    public static Long getAdminUserId(HttpSession session) {
        return (Long) session.getAttribute("adminLoginId");
    }
}
