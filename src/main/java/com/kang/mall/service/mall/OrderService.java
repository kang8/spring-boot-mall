package com.kang.mall.service.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.mall.param.mall.OrderParam;
import com.kang.mall.param.mall.OrderStatusParam;
import com.kang.mall.result.OrderResult;

import java.math.BigDecimal;

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

    /**
     * 查询得到用户的订单数
     *
     * @param page 页码
     * @param size 页面元素
     * @return IPage
     */
    IPage<OrderResult> list(Integer page, Integer size);

    /**
     * 根据订单 ID 查询该订单的信息
     *
     * @param id 订单 ID
     * @return OrderResult
     */
    OrderResult get(Long id);

    /**
     * 根据订单 ID 查询订单的总价
     *
     * @param id 订单 ID
     * @return 订单总价
     */
    OrderResult getTotalPriceById(Long id);

    /**
     * 根据订单 ID 和订单状态参数更新订单
     *
     * @param id               订单 ID
     * @param orderStatusParam 订单状态参数
     * @return 是否更新成功
     */
    boolean update(Long id, OrderStatusParam orderStatusParam);
}
