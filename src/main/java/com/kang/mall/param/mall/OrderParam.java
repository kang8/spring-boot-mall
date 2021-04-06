package com.kang.mall.param.mall;

import com.kang.mall.entity.Goods;
import com.kang.mall.result.CartResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kang
 * ClassName: OrderParam
 * Create Date: 2021/4/6 11:24
 */
@Data
public class OrderParam {
    /**
     * 购物车商品列表
     */
    private List<CartResult> cartGoodsList;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
}
