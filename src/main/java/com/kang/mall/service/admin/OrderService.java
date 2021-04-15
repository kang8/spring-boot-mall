package com.kang.mall.service.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Order;
import com.kang.mall.param.mall.OrderStatusParam;

/**
 * @author kang
 * ClassName: OrderService
 * Create Date: 2021/4/2 21:37
 */
public interface OrderService {
    /**
     * 根据页面和页面大小查询订单
     *
     * @param page 页码
     * @param size 页面大小
     * @return Page
     */
    Page<Order> list(Integer page, Integer size);

    /**
     * 根据订单 ID 和订单状态参数更新订单
     *
     * @param id               订单 ID
     * @param orderStatusParam 订单状态参数
     * @return 是否更新成功
     */
    boolean update(Long id, OrderStatusParam orderStatusParam);
}
