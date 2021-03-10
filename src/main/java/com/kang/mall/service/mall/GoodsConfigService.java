package com.kang.mall.service.mall;

import java.util.List;

/**
 * @author yikang
 * ClassName: GoodsConfigService
 * Create Date: 2021/3/10 17:39
 */
public interface GoodsConfigService {
    /**
     * 根据商品配置类型获取商品配置表中的数据
     *
     * @param type 商品配置类型
     * @return List
     */
    List list(String type);
}
