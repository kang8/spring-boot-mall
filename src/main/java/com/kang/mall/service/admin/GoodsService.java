package com.kang.mall.service.admin;

import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;

/**
 * @author yikang
 * ClassName: GoodsService
 * Create Date: 2021/2/22 17:05
 */
public interface GoodsService {
    /**
     * 根据参数，获取商品信息。返回为一个列表
     *
     * @param page 页码
     * @param size 单页长度
     * @return Result
     */
    Result list(Integer page, Integer size);

    /**
     * 根据 ID 获取对应的商品信息
     *
     * @param id 商品主键 ID
     * @return Result
     */
    Result get(Long id);

    /**
     * 根据传来的商品信息创建商品
     *
     * @param goods 商品信息
     * @return Result
     */
    Result create(Goods goods);

    /**
     * 根据传来的 ID 和商品信息更新商品信息
     *
     * @param id 商品主键 ID
     * @param goods 商品信息
     * @return Result
     */
    Result update(Long id, Goods goods);

    /**
     * 根据 ID 删除对应的商品
     *
     * @param id 商品主键 ID
     * @return Result
     */
    Result remove(Long id);
}
