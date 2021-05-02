package com.kang.mall.service.admin;

import com.kang.mall.result.OrderItemResult;

import java.util.List;

/**
 * @author kang
 * ClassName: OrderItemService
 * Create Date: 2021/4/2 21:37
 */
public interface OrderItemService {
    List<OrderItemResult> getOrderItemByOrderId(Long orderId);
}
