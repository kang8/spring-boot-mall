package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.param.mall.OrderParam;
import com.kang.mall.service.mall.OrderService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author kang
 * ClassName: OrderController
 * Create Date: 2021/4/2 21:34
 */
@RestController
@RequestMapping("/mall")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public Result<Long> create(@RequestBody @Valid OrderParam orderParam) {
        Long orderId = orderService.create(orderParam);
        return ObjectUtils.isNotEmpty(orderId) ? Result.ok(orderId) : Result.error();
    }
}
