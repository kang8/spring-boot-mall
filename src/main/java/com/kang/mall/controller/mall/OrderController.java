package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * ClassName: OrderController
 * Create Date: 2021/4/2 21:34
 */
@RestController
@RequestMapping("/mall")
public class OrderController {

    @PostMapping("/order")
    public Result create() {
        return null;
    }
}
