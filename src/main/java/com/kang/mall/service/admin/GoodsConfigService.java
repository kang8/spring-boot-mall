package com.kang.mall.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.Goods;
import com.kang.mall.entity.GoodsConfig;
import com.kang.mall.param.admin.GoodsConfigParam;
import com.kang.mall.result.GoodsConfigResult;

/**
 * @author yikang
 * ClassName: GoodsConfigService
 * Create Date: 2021/3/9 10:30
 */
public interface GoodsConfigService {
    /**
     * 根据 type 和分页数据查询对应的 goods_index 表
     *
     * @param type 配置类型
     * @param page 页码
     * @param size 单页长度
     * @return IPage<GoodsConfigResult>
     */
    IPage<GoodsConfigResult> list(String type, Integer page, Integer size);

    /**
     * 根据 type 和对应的 id 查询商品配置中的数据
     *
     * @param type 配置类型
     * @param id   商品配置 ID
     * @return GoodsConfigResult
     */
    GoodsConfigResult get(String type, Long id);

    /**
     * 根据 type 和商品配置入参在商品配置中创建一条数据
     *
     * @param type             配置类型
     * @param goodsConfigParam 商品配置入参
     * @return GoodsConfig
     */
    GoodsConfig create(String type, GoodsConfigParam goodsConfigParam);

    /**
     * 根据 type 和商品配置入参在商品配置中更新一条数据
     *
     * @param type             配置类型
     * @param id               商品配置 ID
     * @param goodsConfigParam 商品配置入参
     * @return GoodsConfig
     */
    GoodsConfig update(String type, Long id, GoodsConfigParam goodsConfigParam);

    /**
     * 根据 type 和对应的 id 软删除商品配置表中的数据
     *
     * @param type 配置类型
     * @param id   商品配置 ID
     * @return Boolean
     */
    Boolean remove(String type, Long id);

    /**
     * 商品配置中的选择商品时的列表
     *
     * @param page 页码
     * @param size 单页长度
     * @return Page<Goods>
     */
    Page<Goods> chooseGoodsList(Integer page, Integer size);
}
