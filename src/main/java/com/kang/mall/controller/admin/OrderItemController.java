package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.service.admin.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * ClassName: OrderItemController
 * Create Date: 2021/4/2 21:35
 */
@RestController("OrderItemAdminController")
@RequestMapping("/admin")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/order-item/{orderId}")
    public Result getOrderItemByOrderId(@PathVariable Long orderId) {
        return Result.ok(orderItemService.getOrderItemByOrderId(orderId));
    }
}
