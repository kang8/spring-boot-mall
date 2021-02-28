package com.kang.mall.util;

import com.kang.mall.common.Constants;

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

    public static String removeUploadFilePath(String uploadUrl) {
        int index = uploadUrl.indexOf(Constants.PATH_FOR_ACCESS_UPLOAD_FILE);
        if (index != -1) {
            return uploadUrl.substring(Constants.PATH_FOR_ACCESS_UPLOAD_FILE.length());
        }
        return uploadUrl;
    }
}
