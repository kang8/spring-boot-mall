package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.mall.entity.OrderItem;
import com.kang.mall.result.OrderItemResult;
import com.kang.mall.result.OrderResult;

import java.util.List;

/**
 * @author kang
 * ClassName: OrderItemMapper
 * Create Date: 2021/4/2 21:33
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    List<OrderItemResult> getOrderItemByOrderId(Long orderId);
}
