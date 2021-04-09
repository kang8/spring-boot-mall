package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Order;
import com.kang.mall.result.OrderResult;

/**
 * @author kang
 * ClassName: OrderMapper
 * Create Date: 2021/4/2 21:33
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根据用户 ID 查询订单信息
     *
     * @param orderPage 订单分页信息
     * @param userId 用户 ID
     * @return IPage
     */
    IPage<OrderResult> listPage(Page<Order> orderPage, Long userId);

    /**
     * 根据订单 ID 查询订单信息
     *
     * @param id 订单 ID
     * @return OrderResult
     */
    OrderResult get(Long id);
}
