package com.kang.mall.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Order;
import com.kang.mall.param.mall.OrderStatusParam;
import com.kang.mall.result.OrderResult;
import com.kang.mall.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Result<Page<Order>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return Result.ok(orderService.list(page, size));
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable Long id, @RequestBody @Valid OrderStatusParam orderStatusParam) {
        return orderService.update(id, orderStatusParam) ? Result.ok("更新成功") : Result.error("更新失败");
    }
}
