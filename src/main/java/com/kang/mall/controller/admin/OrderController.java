package com.kang.mall.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.mall.common.Result;
import com.kang.mall.result.OrderResult;
import com.kang.mall.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * ClassName: OrderController
 * Create Date: 2021/4/2 21:35
 */
@RestController("OrderAdminController")
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public Result<IPage<OrderResult>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return Result.ok(orderService.list(page, size));
    }
}
