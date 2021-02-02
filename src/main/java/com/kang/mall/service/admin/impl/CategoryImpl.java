package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Category;
import com.kang.mall.mapper.CategoryMapper;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.result.category.Option;
import com.kang.mall.result.category.Value;
import com.kang.mall.service.admin.CategoryService;
import com.kang.mall.util.ClassUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yikang
 * ClassName: CategoryImpl
 * Create Date: 2021/2/1 16:37
 */
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result list(Long id) {
        Long parentId = 0L;
        if (ObjectUtils.isNotEmpty(id)) {
            parentId = id;
        }
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.orderByDesc("category_rank")
                .eq("parent_id", parentId)
                .select("category_id", "category_level", "parent_id", "category_name", "category_rank", "create_time");

        List<Category> categories = categoryMapper.selectList(query);
        if (categories != null) {

            categories.forEach(category -> {
                if (category.getCategoryLevel() != 3) {
                    category.setHasChildren(true);
                }
            });

            return Result.ok(categories);
        }

        return Result.error("查询失败");
    }

    @Override
    public Result get(Long id) {
        Category category = categoryMapper.selectById(id);
        return category != null ?
                Result.ok("查询成功", category) :
                Result.error("查询失败");
    }

    @Override
    public Result create(CategoryParam categoryParam) {
        Category category = ClassUtil.copyProperties(categoryParam, new Category());
        int isInsert = categoryMapper.insert(category);
        return isInsert > 0 ?
                Result.ok("添加成功", category) :
                Result.error("添加失败");
    }

    @Override
    public Result update(Long id, CategoryParam categoryParam) {
        Category category = categoryMapper.selectById(id);

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

    @Override
    public Result option() {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.ne("category_level", Constants.THIRD_LEVEL)
                .orderByAsc("category_level", "parent_id");
        List<Category> categoryOptions = categoryMapper.selectList(query);

        List<Option> firstOptions = new ArrayList<>();
        Map<Long, List<Option>> optionMap = new HashMap<>(16);

        categoryOptions.forEach(category -> {
            Value value = new Value(category.getParentId(), category.getCategoryId(), category.getCategoryLevel());

            if (Constants.FIRST_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = new ArrayList<>();
                optionMap.put(category.getCategoryId(), options);
                Option option = new Option(category.getCategoryName(), value, options);
                firstOptions.add(option);
            } else if (Constants.SECOND_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = optionMap.get(category.getParentId());
                Option option = new Option(category.getCategoryName(), value, null);
                System.out.println(option);
                options.add(option);
            }

        });

        List<Option> rootList = new ArrayList<>();

        Option rootOption = new Option();
        rootOption.setCategoryName("根节点");
        rootOption.setValue(new Value(-1L, 0L, Constants.ROOT_LEVEL));
        rootOption.setChildren(firstOptions);

        rootList.add(rootOption);

        return Result.ok(rootList);
    }
}
