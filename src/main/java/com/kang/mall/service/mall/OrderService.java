package com.kang.mall.service.mall;

import com.kang.mall.param.mall.OrderParam;

/**
 * @author kang
 * ClassName: OrderService
 * Create Date: 2021/4/2 21:36
 */
public interface OrderService {
    /**
     * 新建订单页面
     *
     * @param orderParam 订单入参
     * @return long 返回 orderId
     */
    Long create(OrderParam orderParam);
}
