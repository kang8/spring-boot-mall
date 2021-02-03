package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.common.Result;
import com.kang.mall.entity.Category;
import com.kang.mall.mapper.CategoryMapper;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.result.category.Option;
import com.kang.mall.service.admin.CategoryService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.JsonUtils;
import com.kang.mall.util.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kang.mall.common.Constants.*;

/**
 * @author yikang
 * ClassName: CategoryImpl
 * Create Date: 2021/2/1 16:37
 */
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JsonUtils jsonUtils;

    @Override
    public Result list() throws JsonProcessingException {
        String categoryList = redisUtils.getValueForString("category_list");
        if (categoryList != null) {
            return Result.ok("查询成功", jsonUtils.toJson(categoryList));
        }

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

            if (FIRST_LEVEL.equals(level)) {
                List<Category> firstChildren = new ArrayList<>();

                category.setChildren(firstChildren);
                firstCategories.add(category);

                secondHashMap.put(category.getCategoryId(), firstChildren);
            } else if (SECOND_LEVEL.equals(level)) {
                List<Category> thirdChildren = new ArrayList<>();
                List<Category> secondCategories = secondHashMap.get(category.getParentId());

                category.setChildren(thirdChildren);
                secondCategories.add(category);

                thirdHaspMap.put(category.getCategoryId(), thirdChildren);
            } else if (THIRD_LEVEL.equals(level)) {
                List<Category> thirdCategories = thirdHaspMap.get(category.getParentId());

                thirdCategories.add(category);
            }
        });

        redisUtils.storeObjectAsJson("category_list", firstCategories);

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
        Category category = ClassUtils.copyProperties(categoryParam, new Category());
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
    public Result option() throws JsonProcessingException {
        String categoryOption = redisUtils.getValueForString("category_option");
        if (categoryOption != null) {
            return Result.ok("查询成功", jsonUtils.toJson(categoryOption));
        }

        QueryWrapper<Category> query = new QueryWrapper<>();
        query.ne("category_level", THIRD_LEVEL)
                .orderByAsc("category_level", "parent_id");
        List<Category> categoryOptions = categoryMapper.selectList(query);

        if (categoryOptions == null) {
            return Result.error("查询失败");
        }

        List<Option> firstOptions = new ArrayList<>();
        Map<Long, List<Option>> optionMap = new HashMap<>(16);

        categoryOptions.forEach(category -> {
            if (FIRST_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = new ArrayList<>();

                optionMap.put(category.getCategoryId(), options);
                Option option = new Option(category.getCategoryId(), category.getCategoryName(), options);

                firstOptions.add(option);
            } else if (SECOND_LEVEL.equals(category.getCategoryLevel())) {
                List<Option> options = optionMap.get(category.getParentId());

                Option option = new Option(category.getCategoryId(), category.getCategoryName(), null);

                options.add(option);
            }
        });

        List<Option> rootList = new ArrayList<>();

        Option rootOption = new Option(ROOT_CATEGORY_ID, ROOT_CATEGORY_NAME, firstOptions);

        rootList.add(rootOption);

        redisUtils.storeObjectAsJson("category_option", rootList);

        return Result.ok(rootList);
    }
}
