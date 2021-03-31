package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Cart;
import com.kang.mall.result.CartResult;

/**
 * @author kang
 * ClassName: CartMapper
 * Create Date: 2021/3/26 16:58
 */
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 查询用户的购物车列表
     *
     * @param page 分页数据
     * @param userId 用户 id
     * @return IPage
     */
    IPage<CartResult> listPage(Page<?> page, Long userId);

    /**
     * 增加商品
     *
     * @param cartId 商品 Id
     * @return int
     */
    int incrementGoodsCount(Long cartId);
}
