package com.kang.mall.service.mall;

import com.kang.mall.entity.Cart;
import com.kang.mall.result.CartResult;

import java.util.List;

/**
 * @author kang
 * ClassName: CartService
 * Create Date: 2021/3/26 17:01
 */
public interface CartService {
    /**
     * 查询用户对应的购物车清单
     *
     * @return IPage
     */
    List<CartResult> list();

    /**
     * 根据商品 id 后去对应的购物车信息
     *
     * @param id 购物车主键 Id
     * @return Cart
     */
    Cart get(String id);

    /**
     * 根据传来的商品 Id 将商品添加到用户的购物车中
     *
     * @param goodsId 商品 Id
     * @return 是否添加成功
     */
    boolean create(Long goodsId);

    /**
     * 根据传来的购物车 ID 和 购物车数量根据购物车数据
     *
     * @param id 购物车 ID
     * @param goodsCount 更新购物车添加的数量
     * @return 是个更新成功
     */
    boolean update(String id, Integer goodsCount);

    /**
     * 根据传来的购物车 Id 删除某行记录
     *
     * @param id 购物车 ID
     * @return 是否删除成功
     */
    boolean remove(String id);
}
