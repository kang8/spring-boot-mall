package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Constants;
import com.kang.mall.entity.Goods;
import com.kang.mall.entity.GoodsConfig;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.GoodsConfigMapper;
import com.kang.mall.mapper.GoodsMapper;
import com.kang.mall.param.admin.GoodsConfigParam;
import com.kang.mall.result.GoodsConfigResult;
import com.kang.mall.service.admin.GoodsConfigService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.CommonUtils;
import com.kang.mall.util.GoodsUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: GoodsConfigServiceImpl
 * Create Date: 2021/3/9 10:31
 */
@Service("GoodsConfigAdminService")
public class GoodsConfigServiceImpl implements GoodsConfigService {

    @Autowired
    private HttpSession session;

    @Autowired
    private GoodsConfigMapper goodsConfigMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public IPage<GoodsConfigResult> list(String type, Integer page, Integer size) {
        return goodsConfigMapper.listPage(new Page<GoodsConfig>(page, size), CommonUtils.getType(type));
    }

    @Override
    public GoodsConfigResult get(String type, Long id) {
        return goodsConfigMapper.get(CommonUtils.getType(type), id);
    }

    @Override
    public GoodsConfig create(String type, GoodsConfigParam goodsConfigParam) {
        validateGoodsId(goodsConfigParam.getGoodsId());
        GoodsConfig goodsConfig = ClassUtils.copyProperties(goodsConfigParam, new GoodsConfig());

        LocalDateTime now = LocalDateTime.now();
        goodsConfig.setCreateTime(now);
        goodsConfig.setUpdateTime(now);
        goodsConfig.setConfigType(CommonUtils.getType(type));
        goodsConfig.setCreateUserAndUpdateUser(session);

        int isCreate = goodsConfigMapper.insert(goodsConfig);
        return isCreate > 0 ? goodsConfig : null;
    }

    @Override
    public GoodsConfig update(String type, Long id, GoodsConfigParam goodsConfigParam) {
        validateGoodsId(goodsConfigParam.getGoodsId());
        QueryWrapper<GoodsConfig> query = new QueryWrapper<>();
        query.eq("config_id", id).eq("config_type", CommonUtils.getType(type));

        GoodsConfig queryGoodsConfig = goodsConfigMapper.selectOne(query);
        if (ObjectUtils.isEmpty(queryGoodsConfig)) {
            throw new CustomizeException(String.format("找不到 type 为 %s, id 为 %d 的数据", type, id));
        }
        BeanUtils.copyProperties(goodsConfigParam, queryGoodsConfig, "createTime", "createUser");

        queryGoodsConfig.setUpdateUser(session);
        queryGoodsConfig.setUpdateTime(LocalDateTime.now());

        int isUpdate = goodsConfigMapper.updateById(queryGoodsConfig);
        return isUpdate > 0 ? queryGoodsConfig : null;
    }

    private void validateGoodsId(Long goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        if (ObjectUtils.isEmpty(goods)) {
            throw new CustomizeException(String.format("找不到商品 ID 为 %d 的商品", goodsId));
        }
        if (!GoodsUtils.isSell(goods.getGoodsSellStatus())) {
            throw new CustomizeException(String.format("ID 为 %d 的商品还未上架", goodsId));
        }
    }

    @Override
    public Boolean remove(String type, Long id) {
        QueryWrapper<GoodsConfig> deleteQuery = new QueryWrapper<>();
        deleteQuery.eq("config_id", id).eq("config_type", CommonUtils.getType(type));

        return goodsConfigMapper.delete(deleteQuery) > 0;
    }

    @Override
    public Page<Goods> chooseGoodsList(Integer page, Integer size, String goods) {
        QueryWrapper<Goods> query = new QueryWrapper<>();
        query.select("goods_id", "goods_name", GoodsUtils.getGoodsCoverImage(), "selling_price")
                .eq("goods_sell_status", Constants.SELLING);
        
        if (ObjectUtils.isNotEmpty(goods)) {
            query.like("goods_name", goods).or()
                    .like("goods_introduce", goods);
        }
        return goodsMapper.selectPage(new Page<>(page, size), query);
    }
}
