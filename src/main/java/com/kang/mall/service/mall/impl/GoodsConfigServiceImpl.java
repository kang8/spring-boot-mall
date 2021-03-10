package com.kang.mall.service.mall.impl;

import com.kang.mall.mapper.GoodsConfigMapper;
import com.kang.mall.service.mall.GoodsConfigService;
import com.kang.mall.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yikang
 * ClassName: GoodsConfigServiceImpl
 * Create Date: 2021/3/10 17:39
 */
@Service
public class GoodsConfigServiceImpl implements GoodsConfigService {
    @Autowired
    private GoodsConfigMapper goodsConfigMapper;

    @Override
    public List list(String type) {
        return goodsConfigMapper.list(CommonUtils.getType(type));
    }
}
