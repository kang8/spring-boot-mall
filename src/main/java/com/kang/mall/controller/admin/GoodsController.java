package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.param.admin.GoodsParam;
import com.kang.mall.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: GoodsController
 * Create Date: 2021/2/22 17:02
 */
@RestController("GoodsAdminController")
@RequestMapping("/admin")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public Result list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return goodsService.list(page, size);
    }

    @GetMapping("/goods/{id}")
    public Result get(@PathVariable("id") Long id) {
        return goodsService.get(id);
    }

    @PostMapping("/goods")
    public Result create(@RequestBody @Valid GoodsParam goodsParam) {
        return goodsService.create(goodsParam);
    }

    @RequestMapping(value = "goods/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable("id") Long id,
                         @RequestBody @Valid GoodsParam goodsParam) {
        return goodsService.update(id, goodsParam);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable("id") Long id) {
        return goodsService.remove(id);
    }
}
