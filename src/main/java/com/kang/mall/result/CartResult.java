package com.kang.mall.result;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * ClassName: CartResult
 * Create Date: 2021/3/30 20:13
 */
@Data
public class CartResult {
    /**
     * 货物车 ID
     */
    private Long cartId;
    /**
     * 商品数量
     */
    private Integer goodsCount;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsCoverImage;
    /**
     * 商品价格
     */
    private BigDecimal sellingPrice;
}
