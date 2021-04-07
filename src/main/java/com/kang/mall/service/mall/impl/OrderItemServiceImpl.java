package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.mall.entity.OrderItem;
import com.kang.mall.mapper.OrderItemMapper;
import com.kang.mall.service.mall.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @author kang
 * ClassName: OrderItemServiceImpl
 * Create Date: 2021/4/2 21:37
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
