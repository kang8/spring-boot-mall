package com.kang.mall.service.admin;

import com.kang.mall.result.OrderItemResult;

import java.util.List;

/**
 * @author kang
 * ClassName: OrderItemService
 * Create Date: 2021/4/2 21:37
 */
public interface OrderItemService {
    /**
     * 通过订单 ID 拿到订单详情列表
     *
     * @param orderId 订单 ID
     * @return 订单详情列表
     */
    List<OrderItemResult> getOrderItemByOrderId(Long orderId);
}
