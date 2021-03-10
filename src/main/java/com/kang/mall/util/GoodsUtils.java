package com.kang.mall.util;

import com.kang.mall.common.Constants;

/**
 * @author yikang
 * ClassName: GoodsUtils
 * Description: 商品工具类
 * Create Date: 2021/3/10 14:20
 */
public class GoodsUtils {
    /**
     * 根据 status 判断商品是否上架
     *
     * @param status 是否上架
     * @return Boolean
     */
    public static Boolean isSell(Byte status) {
        return status.equals(Constants.SELLING);
    }
}
