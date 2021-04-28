package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.mall.common.Constants;
import com.kang.mall.entity.Goods;
import com.kang.mall.entity.Order;
import com.kang.mall.entity.OrderItem;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.CartMapper;
import com.kang.mall.mapper.OrderMapper;
import com.kang.mall.param.mall.OrderParam;
import com.kang.mall.param.mall.OrderStatusParam;
import com.kang.mall.result.CartResult;
import com.kang.mall.result.OrderResult;
import com.kang.mall.service.mall.GoodsService;
import com.kang.mall.service.mall.OrderItemService;
import com.kang.mall.service.mall.OrderService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * ClassName: OrderServiceImpl
 * Create Date: 2021/4/2 21:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HttpSession session;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(OrderParam orderParam) {
        Order order = createOrder(orderParam);
        createOrderItem(orderParam.getCartGoodsList(), order.getOrderId());
        return order.getOrderId();
    }

    @Override
    public IPage<OrderResult> list(Integer page, Integer size) {
        return orderMapper.listPage(new Page<>(page, size), CommonUtils.getUserId(session));
    }

    @Override
    public OrderResult get(Long id) {
        return orderMapper.get(id);
    }

    @Override
    public OrderResult getTotalPriceById(Long id) {
        QueryWrapper<Order> query = new QueryWrapper<>();
        query.eq("order_id", id).select("total_price", "order_status", "create_time");
        Order order = orderMapper.selectOne(query);
        if (ObjectUtils.isEmpty(order)) {
            throw new CustomizeException("该订单不存在");
        }

        OrderResult orderResult = new OrderResult();
        BeanUtils.copyProperties(order, orderResult);
        return orderResult;
    }

    @Override
    public boolean update(Long id, OrderStatusParam orderStatusParam) {
        Order order = orderMapper.selectById(id);
        if (ObjectUtils.isEmpty(order)) {
            throw new CustomizeException("该订单不存在");
        }
        ClassUtils.copyPropertiesNotNull(orderStatusParam, order);
        return orderMapper.updateById(order) > 0;
    }

    private void createOrderItem(List<CartResult> cartGoodsList, Long orderId) {
        if (cartGoodsList.size() <= 0) {
            throw new CustomizeException("请选择要结算的商品");
        }

        List<Long> cartIds = new ArrayList<>(cartGoodsList.size());
        List<Long> goodsIds = new ArrayList<>(cartGoodsList.size());
        List<OrderItem> orderItems = new ArrayList<>(cartGoodsList.size());
        for (CartResult cartResult : cartGoodsList) {
            checkGoodsImage(cartResult);
            cartIds.add(cartResult.getCartId());
            goodsIds.add(cartResult.getGoodsId());

            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(cartResult, orderItem);
            orderItem.setOrderId(orderId);
            orderItems.add(orderItem);
        }

        orderItemService.saveBatch(orderItems);
        cartMapper.deleteBatchIds(cartIds);
        // 清库存
        updateStockNum(goodsIds, cartGoodsList);
    }

    private void checkGoodsImage(CartResult cartResult) {
        String goodsCoverImage = cartResult.getGoodsCoverImage();
        if (goodsCoverImage.startsWith(Constants.PATH_FOR_ACCESS_UPLOAD_FILE)) {
            cartResult.setGoodsCoverImage(goodsCoverImage.substring(Constants.PATH_FOR_ACCESS_UPLOAD_FILE.length()));
        }
    }

    private void updateStockNum(List<Long> goodsIds, List<CartResult> cartGoodsList) {
        QueryChainWrapper<Goods> query = new QueryChainWrapper<>(goodsService.getBaseMapper());
        List<Goods> goodsList = query.in("goods_id", goodsIds).eq("goods_sell_status", Constants.SELLING).select("goods_id", "stock_num").list();

        if (goodsIds.size() != cartGoodsList.size()) {
            throw new CustomizeException("不好意思，购买商品中有下架商品。请检查后重新结算");
        }

        for (int i = 0; i < cartGoodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            CartResult cartResult = cartGoodsList.get(i);
            if (goods.getStockNum() < cartResult.getGoodsCount()) {
                throw new CustomizeException(cartGoodsList.get(i).getGoodsName() + " 超过商品库存了");
            }
            goods.setStockNum(goods.getStockNum() - cartResult.getGoodsCount());
        }

        goodsService.updateBatchById(goodsList);
    }

    private Order createOrder(OrderParam orderParam) {
        Long userId = CommonUtils.getUserId(session);

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(orderParam.getTotalPrice());
        order.setUsername(orderParam.getUsername());
        CommonUtils.validatePhoneNumberNotPassThrowException(orderParam.getPhone());
        order.setPhone(orderParam.getPhone());
        order.setAddress(orderParam.getAddress());
        int insert = orderMapper.insert(order);
        if (insert < 0) {
            throw new CustomizeException("创建订单失败");
        }
        return order;
    }
}
