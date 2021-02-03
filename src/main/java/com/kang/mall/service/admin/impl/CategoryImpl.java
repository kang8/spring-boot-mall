package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Category;
import com.kang.mall.mapper.CategoryMapper;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.result.category.Option;
import com.kang.mall.service.admin.CategoryService;
import com.kang.mall.util.ClassUtil;
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
    public Result list() {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query
                .orderByAsc("category_level", "parent_id")
                .orderByDesc("category_rank")
                .select("category_id", "category_level", "parent_id", "category_name", "category_rank", "create_time");

        List<Category> categories = categoryMapper.selectList(query);
        if (categories == null) {
            return Result.error("查询失败");
        }

        Map<Long, List<Category>> secondHashMap = new HashMap<>(8);
        Map<Long, List<Category>> thirdHaspMap = new HashMap<>(20);
        List<Category> firstCategories = new ArrayList<>();

        categories.forEach(category -> {
            Byte level = category.getCategoryLevel();

            if (Constants.FIRST_LEVEL.equals(level)) {
                List<Category> firstChildren = new ArrayList<>();

                category.setChildren(firstChildren);
                firstCategories.add(category);

                secondHashMap.put(category.getCategoryId(), firstChildren);
            } else if (Constants.SECOND_LEVEL.equals(level)) {
                List<Category> thirdChildren = new ArrayList<>();
                List<Category> secondCategories = secondHashMap.get(category.getParentId());

                category.setChildren(thirdChildren);
                secondCategories.add(category);

                thirdHaspMap.put(category.getCategoryId(), thirdChildren);
            } else if (Constants.THIRD_LEVEL.equals(level)) {
                List<Category> thirdCategories = thirdHaspMap.get(category.getParentId());

                thirdCategories.add(category);
            }
        });

        return Result.ok(firstCategories);

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

        if (categoryOptions == null) {
            return Result.error("查询失败");
        }

        List<Option> firstOptions = new ArrayList<>();
        Map<Long, List<Option>> optionMap = new HashMap<>(16);

        categoryOptions.forEach(category -> {
            if (Constants.FIRST_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = new ArrayList<>();

                optionMap.put(category.getCategoryId(), options);
                Option option = new Option(category.getCategoryId(), category.getCategoryName(), options);

                firstOptions.add(option);
            } else if (Constants.SECOND_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = optionMap.get(category.getParentId());

                Option option = new Option(category.getCategoryId(), category.getCategoryName(), null);

                options.add(option);
            }
        });

        List<Option> rootList = new ArrayList<>();

        Option rootOption = new Option();
        rootOption.setCategoryName("根节点");
        rootOption.setChildren(firstOptions);
        rootOption.setCategoryId(0L);

        rootList.add(rootOption);

        return Result.ok(rootList);
    }
}
