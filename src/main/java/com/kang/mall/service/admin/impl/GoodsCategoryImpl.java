package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Result;
import com.kang.mall.entity.GoodsCategory;
import com.kang.mall.mapper.GoodsCategoryMapper;
import com.kang.mall.param.admin.GoodsCategoryParam;
import com.kang.mall.service.admin.GoodsCategoryService;
import com.kang.mall.util.ClassUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yikang
 * ClassName: GoodsCategoryImpl
 * Create Date: 2021/2/1 16:37
 */
@Service
public class GoodsCategoryImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Override
    public Result list() {
        QueryWrapper<GoodsCategory> query = new QueryWrapper<>();
        query.orderByDesc("category_rank")
                .select("category_id", "category_level", "parent_id", "category_name", "category_rank");

        List<GoodsCategory> categories = categoryMapper.selectList(query);

        return categories != null ?
                Result.ok(categories) :
                Result.error("查询失败");
    }

    @Override
    public Result get(Long id) {
        GoodsCategory goodsCategory = categoryMapper.selectById(id);
        return goodsCategory != null ?
                Result.ok("查询成功", goodsCategory) :
                Result.error("查询失败");
    }

    @Override
    public Result create(GoodsCategoryParam categoryParam) {
        GoodsCategory category = ClassUtil.copyProperties(categoryParam, new GoodsCategory());
        int isInsert = categoryMapper.insert(category);
        return isInsert > 0 ?
                Result.ok("添加成功", category) :
                Result.error("添加失败");
    }

    @Override
    public Result update(Long id, GoodsCategoryParam categoryParam) {
        GoodsCategory category = categoryMapper.selectById(id);

        BeanUtils.copyProperties(categoryParam, category, "createUser", "createTime");
        category.setUpdateTime(LocalDateTime.now());
        int isUpdate = categoryMapper.updateById(category);

        return isUpdate > 0 ?
                Result.ok("更新成功", category) :
                Result.error("更新失败");
    }

    @Override
    public Result remove(Long id) {
        int isDelete = categoryMapper.deleteById(id);
        return isDelete > 0 ?
                Result.ok("删除成功") :
                Result.error("删除失败");
    }
}
