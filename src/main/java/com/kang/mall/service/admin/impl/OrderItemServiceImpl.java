package com.kang.mall.service.admin.impl;

import com.kang.mall.mapper.OrderItemMapper;
import com.kang.mall.result.OrderItemResult;
import com.kang.mall.service.admin.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kang
 * ClassName: OrderItemServiceImpl
 * Create Date: 2021/4/2 21:38
 */
@Service("OrderItemAdminService")
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResult> getOrderItemByOrderId(Long orderId) {
        return orderItemMapper.getOrderItemByOrderId(orderId);
    }
}
