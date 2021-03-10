package com.kang.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.GoodsConfig;
import com.kang.mall.result.GoodsConfigResult;

/**
 * @author yikang
 * ClassName: GoodsConfigMapper
 * Create Date: 2021/3/9 10:30
 */
public interface GoodsConfigMapper extends BaseMapper<GoodsConfig> {
    /**
     * 根据 type 和 goodsId 获取商品配置表和商品表对应的数据
     *
     * @param type 商品配置表类型
     * @param id 商品 ID
     * @return GoodsConfigResult
     */
    GoodsConfigResult get(Byte type, Long id);

    /**
     * 根据 type 和分类数据获取商品配置表和商品表对应的数据
     *
     * @param page 分类配置
     * @param type 商品配置表类型
     * @return IPage
     */
    IPage<GoodsConfigResult> listPage(Page<?> page, Byte type);
}
