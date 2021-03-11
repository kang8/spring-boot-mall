package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Goods;
import com.kang.mall.mapper.GoodsMapper;
import com.kang.mall.param.admin.GoodsParam;
import com.kang.mall.service.admin.GoodsService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.CommonUtils;
import com.kang.mall.util.GoodsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: GoodsServiceImpl
 * Create Date: 2021/2/22 17:26
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private HttpSession session;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Result list(Integer page, Integer size) {
        QueryWrapper<Goods> query = new QueryWrapper<>();

        query.select("goods_id", "category_id", GoodsUtils.getGoodsCoverImage(), "goods_name", "goods_introduce",
                "goods_detail_content", "original_price", "selling_price",
                "stock_num", "tag", "goods_sell_status", "create_time");
        Page<Goods> goodsPage = goodsMapper.selectPage(new Page<>(page, size), query);

        return Result.ok(goodsPage);
    }

    @Override
    public Result get(Long id) {
        Goods goods = goodsMapper.selectById(id);
        return Result.ok(goods);
    }

    @Override
    public Result create(GoodsParam goodsParam) {
        Goods goods = ClassUtils.copyProperties(goodsParam, new Goods());
        goods.setCreateUserAndUpdateUser(session);
        LocalDateTime now = LocalDateTime.now();
        goods.setCreateTime(now);
        goods.setUpdateTime(now);
        String originGoodsCoverImage = goods.getGoodsCoverImage();
        goods.setGoodsCoverImage(CommonUtils.removeUploadFilePath(originGoodsCoverImage));
        int isInsert = goodsMapper.insert(goods);

        goods.setGoodsCoverImage(originGoodsCoverImage);
        return isInsert > 0 ?
                Result.ok("添加成功", goods) :
                Result.error("添加失败");
    }

    @Override
    public Result update(Long id, GoodsParam goodsParam) {
        Goods queryGoods = goodsMapper.selectById(id);

        BeanUtils.copyProperties(goodsParam, queryGoods, "createTime", "createUser");
        queryGoods.setUpdateUser(session);
        String originGoodsCoverImage = queryGoods.getGoodsCoverImage();
        queryGoods.setGoodsCoverImage(CommonUtils.removeUploadFilePath(originGoodsCoverImage));
        queryGoods.setUpdateTime(LocalDateTime.now());
        int isUpdate = goodsMapper.updateById(queryGoods);

        queryGoods.setGoodsCoverImage(originGoodsCoverImage);
        return isUpdate > 0 ?
                Result.ok("更新成功", queryGoods) :
                Result.error("更新失败");
    }

    @Override
    public Result remove(Long id) {
        int isDelete = goodsMapper.deleteById(id);
        return isDelete > 0 ?
                Result.ok("删除成功") :
                Result.error("删除失败");
    }
}
