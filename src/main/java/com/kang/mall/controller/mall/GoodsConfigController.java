package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.service.mall.GoodsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yikang
 * ClassName: GoodsConfigService
 * Create Date: 2021/3/10 17:37
 */
@RestController
public class GoodsConfigController {

    @Autowired
    private GoodsConfigService goodsConfigService;

    @GetMapping("/config/{type}/goods")
    public Result list(@PathVariable String type) {
        List list = goodsConfigService.list(type);
        return Result.ok(list);
    }
}
