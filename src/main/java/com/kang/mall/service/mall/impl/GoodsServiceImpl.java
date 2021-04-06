package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Constants;
import com.kang.mall.entity.Goods;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.GoodsMapper;
import com.kang.mall.param.mall.SearchParam;
import com.kang.mall.service.mall.GoodsService;
import com.kang.mall.util.GoodsUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kang
 * ClassName: GoodsServiceImpl
 * Create Date: 2021/3/22 11:32
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Page search(SearchParam searchParam) {
        QueryWrapper<Goods> query = getGoodsQueryWrapper(searchParam);
        return goodsMapper.selectPage(
                new Page<>(searchParam.getPage(), searchParam.getSize()),
                query);
    }

    @Override
    public Goods get(Long id) {
        QueryWrapper<Goods> query = new QueryWrapper<>();
        query.eq("goods_id", id).eq("goods_sell_status", Constants.SELLING)
                .select("goods_name", "goods_introduce", GoodsUtils.getGoodsCoverImage(),
                        "goods_detail_content", "original_price", "selling_price", "stock_num");
        return goodsMapper.selectOne(query);
    }

    private QueryWrapper<Goods> getGoodsQueryWrapper(SearchParam searchParam) {
        QueryWrapper<Goods> query = new QueryWrapper<>();

        selectColumn(query);
        filter(query, searchParam.getKeyword(), searchParam.getCategoryId());
        sort(query, searchParam.getSortRule());

        return query;
    }

    private void selectColumn(QueryWrapper<Goods> query) {
        query.select(GoodsUtils.getGoodsCoverImage(), "goods_id", "goods_name", "selling_price", "goods_introduce");
    }

    private void filter(QueryWrapper<Goods> query, String keyword, Long categoryId) {
        if (ObjectUtils.isNotEmpty(keyword)) {
            query.like("goods_name", keyword).or()
                    .like("goods_introduce", keyword);
        } else if (ObjectUtils.isNotEmpty(categoryId)) {
            query.eq("category_id", categoryId);
        }
    }

    private void sort(QueryWrapper<Goods> query, String rule) {
        switch (rule) {
            case Constants.SORT_RULE_NORMAL:
                query.orderByDesc("stock_num");
                break;
            case Constants.SORT_RULE_NEW:
                query.orderByDesc("create_time");
                break;
            case Constants.SORT_RULE_PRICE:
                query.orderByAsc("selling_price");
                break;
            default:
                throw new CustomizeException("没有这个排序规则");
        }
    }
}
