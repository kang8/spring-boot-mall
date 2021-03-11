package com.kang.mall.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;
import com.kang.mall.entity.GoodsConfig;
import com.kang.mall.param.admin.GoodsConfigParam;
import com.kang.mall.result.GoodsConfigResult;
import com.kang.mall.service.admin.GoodsConfigService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: GoodsConfigController
 * Create Date: 2021/3/9 10:28
 */
@RestController("GoodsConfigAdminController")
@RequestMapping("/admin")
public class GoodsConfigController {

    @Autowired
    private GoodsConfigService goodsConfigService;

    @GetMapping("/config/{type}/goods")
    public Result<IPage<GoodsConfigResult>> list(
            @PathVariable String type,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        IPage<GoodsConfigResult> list = goodsConfigService.list(type, page, size);
        return Result.ok(list);
    }

    @GetMapping("/config/choose-goods")
    public Result chooseGoodsList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Page<Goods> goodsPage = goodsConfigService.chooseGoodsList(page, size);
        return Result.ok(goodsPage);
    }

    @GetMapping("/config/{type}/goods/{id}")
    public Result<GoodsConfigResult> get(@PathVariable String type,
                                         @PathVariable Long id) {
        GoodsConfigResult goodsConfigResult = goodsConfigService.get(type, id);
        return Result.ok(goodsConfigResult);
    }

    @PostMapping("/config/{type}/goods")
    public Result<GoodsConfig> create(@PathVariable String type,
                                      @RequestBody @Valid GoodsConfigParam goodsConfigParam) {
        GoodsConfig goodsConfig = goodsConfigService.create(type, goodsConfigParam);
        return ObjectUtils.isEmpty(goodsConfig) ?
                Result.error("创建失败") :
                Result.ok("创建成功", goodsConfig);
    }

    @RequestMapping(value = "/config/{type}/goods/{id}", method = RequestMethod.PUT)
    public Result<GoodsConfig> update(@PathVariable String type,
                                      @PathVariable Long id,
                                      @RequestBody @Valid GoodsConfigParam goodsConfigParam) {
        GoodsConfig goodsConfig = goodsConfigService.update(type, id, goodsConfigParam);
        return ObjectUtils.isEmpty(goodsConfig) ?
                Result.error("更新失败") :
                Result.ok("更新成功", goodsConfig);
    }

    @RequestMapping(value = "/config/{type}/goods/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String type,
                         @PathVariable Long id) {
        return goodsConfigService.remove(type, id) ?
                Result.ok("删除成功") :
                Result.error("删除失败");
    }
}
