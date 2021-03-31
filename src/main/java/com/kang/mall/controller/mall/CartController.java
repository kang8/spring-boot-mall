package com.kang.mall.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kang.mall.common.Result;
import com.kang.mall.result.CartResult;
import com.kang.mall.service.mall.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kang
 * ClassName: CartController
 * Create Date: 2021/3/26 17:00
 */
@RestController
@RequestMapping("/mall")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public Result list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        IPage<CartResult> list = cartService.list(page, size);
        return Result.ok(list);
    }

    @GetMapping("/cart/{id}")
    public Result get(@PathVariable String id) {
        cartService.get(id);
        return null;
    }

    @PostMapping("/cart")
    public Result create(Long goodsId) {
        return cartService.create(goodsId) ? Result.ok("添加成功") : Result.error();
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id, Long goodsCount) {
        return cartService.update(id, goodsCount) ? Result.ok() : Result.error();
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {
        return cartService.remove(id) ? Result.ok() : Result.error();
    }

}
