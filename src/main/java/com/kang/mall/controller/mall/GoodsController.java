package com.kang.mall.controller.mall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;
import com.kang.mall.param.mall.SearchParam;
import com.kang.mall.service.mall.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * ClassName: GoodsController
 * Create Date: 2021/3/22 11:31
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/search")
    public Result<Page> search(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "12") Integer size,
            String keyword,
            @RequestParam() String sortRule,
            Long categoryId
    ) {
        SearchParam searchParam = new SearchParam(keyword, categoryId, sortRule, size, page);
        Page search = goodsService.search(searchParam);
        return Result.ok(search);
    }

    @GetMapping("goods/{id}")
    public Result<Goods> get(@PathVariable("id") Long id) {
        Goods goods = goodsService.get(id);
        return Result.ok(goods);
    }
}
