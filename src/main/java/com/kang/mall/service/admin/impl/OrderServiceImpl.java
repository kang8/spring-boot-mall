package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Order;
import com.kang.mall.mapper.OrderMapper;
import com.kang.mall.param.mall.OrderStatusParam;
import com.kang.mall.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kang
 * ClassName: OrderServiceImpl
 * Create Date: 2021/4/2 21:38
 */
@Service("OrderAdminService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private com.kang.mall.service.mall.OrderService mallOrderService;

    @Override
    public Page<Order> list(Integer page, Integer size) {
        QueryWrapper<Order> query = new QueryWrapper<>();
        query.select("order_id", "user_id", "total_price", "pay_type", "order_status", "username", "phone", "address", "create_time");
        return orderMapper.selectPage(new Page<>(page, size), query);
    }

    @Override
    public boolean update(Long id, OrderStatusParam orderStatusParam) {
        mallOrderService.update(id, orderStatusParam);
        return false;
    }
}
