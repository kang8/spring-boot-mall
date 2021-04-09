package com.kang.mall.result;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * ClassName: OrderItemResult
 * Create Date: 2021/4/9 15:36
 */
@Data
public class OrderItemResult {
    /**
     * 商品 ID
     */
    private Long goodsId;
    /**
     * 买下该商品时的名字
     */
    private String goodsName;
    /**
     * 买下该商品时的图片
     */
    private String goodsCoverImage;
    /**
     * 买下该商品时的价格
     */
    private BigDecimal sellingPrice;
    /**
     * 商品数量
     */
    private Integer goodsCount;
}
