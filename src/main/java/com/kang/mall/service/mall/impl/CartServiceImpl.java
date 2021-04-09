package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.entity.Cart;
import com.kang.mall.entity.Goods;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.CartMapper;
import com.kang.mall.mapper.GoodsMapper;
import com.kang.mall.result.CartResult;
import com.kang.mall.service.mall.CartService;
import com.kang.mall.util.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<CartResult> list() {
        Long userId = CommonUtils.getUserId(session);
        return cartMapper.listPage(userId);
    }

    @Override
    public Cart get(String id) {
        return null;
    }

    @Override
    public boolean create(Long goodsId) {
        Long userId = CommonUtils.getUserId(session);

        Cart queryCart = searchCart(goodsId, userId);
        Goods goods = goodsMapper.selectById(goodsId);
        if (ObjectUtils.isNotEmpty(queryCart)) {
            checkStockNum(queryCart.getGoodsCount(), goods.getStockNum(), 1);

            int increment = cartMapper.incrementGoodsCount(queryCart.getCartId());
            return increment > 0;
        }
        checkStockNum(0, goods.getStockNum(), 1);

        Cart cart = new Cart(userId, goodsId);
        int insert = cartMapper.insert(cart);
        return insert > 0;
    }

    private void checkStockNum(Integer goodsCount, Integer stockNum) {
        if (stockNum < goodsCount) {
            throw new CustomizeException("购物车数量已经超过商品库存了");
        }
    }

    private void checkStockNum(Integer goodsCount, Integer stockNum, Integer incGoodsCount) {
        if (stockNum < goodsCount + incGoodsCount) {
            throw new CustomizeException("购物车数量已经超过商品库存了");
        }
    }

    private Cart searchCart(Long goodsId, Long userId) {
        QueryWrapper<Cart> query = new QueryWrapper<>();
        query.eq("goods_id", goodsId).eq("user_id", userId);
        return cartMapper.selectOne(query);
    }

    @Override
    public boolean update(Long cartId, Integer goodsCount) {
        Long userId = CommonUtils.getUserId(session);
        QueryWrapper<Cart> query = new QueryWrapper<>();
        query.eq("cart_id", cartId).eq("user_id", userId).select("cart_id", "goods_id");
        Cart cart = cartMapper.selectOne(query);
        if (ObjectUtils.isEmpty(cart)) {
            throw new CustomizeException("该商品没有添加到购物车中");
        }

        Goods goods = goodsMapper.selectById(cart.getGoodsId());
        checkStockNum(goodsCount, goods.getStockNum());

        cart.setGoodsCount(goodsCount);
        cart.setUpdateTime(LocalDateTime.now());

        int isUpdate = cartMapper.updateById(cart);
        return isUpdate > 0;
    }

    @Override
    public boolean remove(Long id) {
        return cartMapper.deleteById(id) > 0;
    }

    @Override
    public Integer getTotalCount() {
        Long userId = CommonUtils.getUserId(session);
        QueryWrapper<Cart> query = new QueryWrapper<>();
        query.select("sum(goods_count) AS goodsCount").eq("user_id", userId);
        Cart cart = cartMapper.selectOne(query);
        return cart == null ? 0 : cart.getGoodsCount();
    }
}
