package com.kang.mall.service.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
    Page list(Integer page, Integer size);
}
