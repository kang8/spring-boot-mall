package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.mall.entity.OrderItem;
import com.kang.mall.result.OrderItemResult;

import java.util.List;

/**
 * @author kang
 * ClassName: OrderItemMapper
 * Create Date: 2021/4/2 21:33
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    /**
     * 通过订单 ID 拿到订单详情列表
     *
     * @param orderId 订单 ID
     * @return 订单详情列表
     */
    List<OrderItemResult> getOrderItemByOrderId(Long orderId);
}
