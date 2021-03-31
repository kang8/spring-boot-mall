package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Cart;
import com.kang.mall.mapper.CartMapper;
import com.kang.mall.result.CartResult;
import com.kang.mall.service.mall.CartService;
import com.kang.mall.util.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author kang
 * ClassName: CartServiceImpl
 * Create Date: 2021/3/26 17:01
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private HttpSession session;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public IPage<CartResult> list(Integer page, Integer size) {
        Long userId = CommonUtils.getUserId(session);
        return cartMapper.listPage(new Page<Cart>(page, size), userId);
    }

    @Override
    public Cart get(String id) {
        return null;
    }

    @Override
    public boolean create(Long goodsId) {
        Long userId = CommonUtils.getUserId(session);

        Cart queryCart = searchCart(goodsId, userId);
        if (ObjectUtils.isNotEmpty(queryCart)) {
            int increment = cartMapper.incrementGoodsCount(queryCart.getCartId());
            return increment > 0;
        }

        Cart cart = new Cart(userId, goodsId);
        int insert = cartMapper.insert(cart);
        return insert > 0;
    }

    private Cart searchCart(Long goodsId, Long userId) {
        QueryWrapper<Cart> query = new QueryWrapper<>();
        query.eq("goods_id", goodsId).eq("user_id", userId);
        return cartMapper.selectOne(query);
    }

    @Override
    public boolean update(String id, Long goodsCount) {
        return false;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }
}
