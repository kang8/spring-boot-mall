package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.mall.entity.Cart;
import com.kang.mall.result.CartResult;

import java.util.List;

/**
 * @author kang
 * ClassName: CartMapper
 * Create Date: 2021/3/26 16:58
 */
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 查询用户的购物车列表
     *
     * @param userId 用户 id
     * @return IPage
     */
    List<CartResult> listPage(Long userId);

    /**
     * 增加商品
     *
     * @param cartId 商品 Id
     * @return int
     */
    int incrementGoodsCount(Long cartId);

    /**
     * 根据 ID 硬删除购物车数据
     *
     * @param id 购物车 ID
     * @return 是否成功删除
     */
    int hardDeleteById(Long id);
}
