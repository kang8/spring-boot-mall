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
    public static Boolean isSell(Boolean status) {
        return status.equals(Constants.SELLING);
    }

    /**
     * 从数据库拿到的 URL 要加上 Constants 中设置的值
     * 该方法就是判断 URL 是本地还是外部的链接，如果是本地，就将 goods_cover_image 加上该值
     *
     * @return String
     */
    public static String getGoodsCoverImage() {
        return CommonUtils.queryUrl(Constants.TABLE_FIELD_GOODS_COVER_IMAGE, Constants.ENTITY_FIELD_GOODS_COVER_IMAGE);
    }
}
