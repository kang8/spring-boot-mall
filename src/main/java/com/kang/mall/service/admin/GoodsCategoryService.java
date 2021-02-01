package com.kang.mall.service.admin;

import com.kang.mall.common.Result;
import com.kang.mall.param.admin.GoodsCategoryParam;

/**
 * @author yikang
 * ClassName: GoodsCategoryService
 * Create Date: 2021/2/1 16:37
 */
public interface GoodsCategoryService {
    /**
     * 获取商品分类信息
     *
     * @return Result
     */
    Result list();

    /**
     * 根据商品分类 Id 获取商品分类信息
     *
     * @param id 商品分类 ID
     * @return Result
     */
    Result get(Long id);

    /**
     * 创建商品分类信息
     *
     * @param categoryParam 商品分类参数
     * @return Result
     */
    Result create(GoodsCategoryParam categoryParam);

    /**
     * 更新商品分类信息
     *
     * @param id 商品分类 ID
     * @param categoryParam 商品分类参数
     * @return Result
     */
    Result update(Long id, GoodsCategoryParam categoryParam);

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类 ID
     * @return Result
     */
    Result remove(Long id);
}
