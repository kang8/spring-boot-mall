package com.kang.mall.service.mall.impl;

import com.kang.mall.mapper.OrderMapper;
import com.kang.mall.param.mall.OrderParam;
import com.kang.mall.service.mall.OrderService;
import com.kang.mall.util.CommonUtils;
import com.kang.mall.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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
    private RedisUtils redisUtils;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void create(OrderParam orderParam) {
        Long userId = CommonUtils.getUserId(session);

        createOrder();

        System.out.println(orderParam);
    }

    private void createOrder() {
    }
}
