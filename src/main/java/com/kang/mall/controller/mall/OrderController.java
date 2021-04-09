package com.kang.mall.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.mall.common.Result;
import com.kang.mall.param.mall.OrderParam;
import com.kang.mall.result.OrderResult;
import com.kang.mall.service.mall.OrderService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/order")
    public Result<IPage<OrderResult>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        IPage<OrderResult> list = orderService.list(page, size);
        return Result.ok(list);
    }

    @PostMapping("/order")
    public Result<Long> create(@RequestBody @Valid OrderParam orderParam) {
        Long orderId = orderService.create(orderParam);
        return ObjectUtils.isNotEmpty(orderId) ? Result.ok(orderId) : Result.error();
    }
}
