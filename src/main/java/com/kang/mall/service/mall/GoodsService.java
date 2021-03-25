package com.kang.mall.service.mall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;
import com.kang.mall.param.mall.SearchParam;

/**
 * @author kang
 * ClassName: GoodsService
 * Create Date: 2021/3/22 11:32
 */
public interface GoodsService {
    /**
     * 根据传参查询商品
     *
     * @param searchParam 查询入参
     * @return Page
     */
    Page search(SearchParam searchParam);

    /**
     * 根据商品 ID 获取商品内容
     *
     * @param id 商品 Id
     * @return Goods
     */
    Goods get(Long id);
}
