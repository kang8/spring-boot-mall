package com.kang.mall.util;

import com.kang.mall.common.Constants;
import com.kang.mall.exception.CustomizeException;

import javax.servlet.http.HttpSession;

/**
 * @author yikang
 * ClassName: CommonUtils
 * Create Date: 2021/2/1 19:43
 */
public class CommonUtils {

    public static Long getAdminUserId(HttpSession session) {
        return (Long) session.getAttribute(Constants.ADMIN_LOGIN_CREDENTIAL);
    }

    public static Long getUserId(HttpSession session) {
        return (Long) session.getAttribute(Constants.MALL_LOGIN_CREDENTIAL);
    }

    public static String removeUploadFilePath(String uploadUrl) {
        int index = uploadUrl.indexOf(Constants.PATH_FOR_ACCESS_UPLOAD_FILE);
        if (index != -1) {
            return uploadUrl.substring(Constants.PATH_FOR_ACCESS_UPLOAD_FILE.length());
        }
        return uploadUrl;
    }

    /**
     * 由于数据库中可能会存外界的资源，也就是一个完整的 URL，
     * 所以这里要判断一下该资源是本地的还是外界的，判断方式很简单。
     * 本地的资源都是 '/' 开头，所以只需判断该字符串的第一个字符即可。
     * <p>
     * 这里用到了较多的 mysql 函数：
     * <p>
     * IF(condition, true, false)
     * 如果 condition 条件为真，则展示后面的 true 参数，反之展示 false
     * <p>
     * LEFT(string, 1)
     * 获取 string 参数的第 1 个字符。
     * 注：left 函数默认认为下标从 1 开始
     * <p>
     * CONCAT(str1, str2, ...)
     * 连接 str1, str2 等字符串成一个整体字符串
     *
     * @param tableField  数据表中的字段 一般为蛇形命名格式
     * @param entityField 实体表中的字段 一般为小驼峰命名格式
     * @return 查询
     */
    public static String queryUrl(String tableField, String entityField) {
        // IF(LEFT(tableField, 1) = '/', CONCAT('/file', tableField), tableField) AS entityField
        return "IF(LEFT(" + tableField + ", 1) = '/', CONCAT('" +
                Constants.PATH_FOR_ACCESS_UPLOAD_FILE + "', " +
                tableField + "), " + tableField + ") AS " + entityField;
    }

    /**
     * 将字符串的 type 转换成 Byte 类型，以储存在数据库中
     *
     * @param type 传入的类型
     * @return Byte
     */
    public static Byte getType(String type) {
        String lowerType = type.toLowerCase();
        switch (lowerType) {
            case Constants.NEW:
                return Constants.TYPE_FOR_NEW;
            case Constants.HOT:
                return Constants.TYPE_FOR_HOT;
            case Constants.RECOMMENDATION:
                return Constants.TYPE_FOR_RECOMMENDATION;
            default:
                throw new CustomizeException(String.format("没有 %s 这个类型", type));
        }
    }

    /**
     * 使用正则表达式判断手机号码是否合法
     * @param phone 手机号码
     * @return 是否为正确的手机号码
     */
    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("^1[3-9]\\d{9}$");
    }
}
