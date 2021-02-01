package com.kang.mall.util;

import javax.servlet.http.HttpSession;

/**
 * @author yikang
 * ClassName: CommonUtil
 * Create Date: 2021/2/1 19:43
 */
public class CommonUtil {

    public static Long getUserId(HttpSession session, String param) {
        return (Long) session.getAttribute(param);
    }
}
