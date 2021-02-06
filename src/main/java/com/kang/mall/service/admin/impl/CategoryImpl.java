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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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

            if (hasFirstLevel(level)) {
                List<Category> firstChildren = new ArrayList<>();

                category.setChildren(firstChildren);
                firstCategories.add(category);

                secondHashMap.put(category.getCategoryId(), firstChildren);
            } else if (hasSecondLevel(level)) {
                List<Category> thirdChildren = new ArrayList<>();
                List<Category> secondCategories = secondHashMap.get(category.getParentId());

                category.setChildren(thirdChildren);
                secondCategories.add(category);

                thirdHaspMap.put(category.getCategoryId(), thirdChildren);
            } else if (hasThirdLevel(level)) {
                List<Category> thirdCategories = thirdHaspMap.get(category.getParentId());

                thirdCategories.add(category);
            }
        });

        redisUtils.storeObjectAsJson("category_list", firstCategories);

        return Result.ok(firstCategories);
    }

    private boolean hasThirdLevel(Byte level) {
        return THIRD_LEVEL.equals(level);
    }

    private boolean hasFirstLevel(Byte level) {
        return FIRST_LEVEL.equals(level);
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
        byte level;
        Category parentCategory = categoryMapper.selectById(categoryParam.getParentId());
        if (parentCategory == null) {
            level = 1;
        } else {
            level = (byte) (parentCategory.getCategoryLevel() + 1);
        }


        Category category = ClassUtils.copyProperties(categoryParam, new Category());
        category.setCategoryLevel(level);
        int isInsert = categoryMapper.insert(category);

        cleanCacheByParentId(category.getParentId());

        return isInsert > 0 ?
                Result.ok("添加成功", category) :
                Result.error("添加失败");
    }

    private void cleanCacheByParentId(Long parentId) {
        redisUtils.deleteKey("category_list");
        Category parentCategory = categoryMapper.selectById(parentId);
        if (parentCategory == null || !THIRD_LEVEL.equals(parentCategory.getCategoryLevel())) {
            redisUtils.deleteKey("category_option");
        }
    }

    @Override
    public Result update(Long id, CategoryParam categoryParam) {
        if (categoryParam.getParentId().equals(id)) {
            return Result.error("父节点不能选择标签");
        }

        Category category = categoryMapper.selectById(id);
        byte level;
        Category parentCategory = categoryMapper.selectById(categoryParam.getParentId());
        if (parentCategory == null) {
            level = 1;
        } else {
            level = (byte) (parentCategory.getCategoryLevel() + 1);
        }

        BeanUtils.copyProperties(categoryParam, category, "createUser", "createTime");
        category.setUpdateTime(LocalDateTime.now());
        category.setCategoryLevel(level);
        int isUpdate = categoryMapper.updateById(category);

        cleanCacheByParentId(category.getParentId());

        return isUpdate > 0 ?
                Result.ok("更新成功", category) :
                Result.error("更新失败");
    }

    @Override
    @Transactional
    public Result remove(Long id) {
        Category category = categoryMapper.selectById(id);

        if (category == null) {
            return Result.error("没有该分类");
        }

        List<Long> deleteArray = new LinkedList<>();
        deleteChildren(category, deleteArray);
        categoryMapper.deleteBatchIds(deleteArray);
        redisUtils.deleteKey("category_list");

        return Result.ok("删除成功");
    }

    /**
     * 使用递归来查找要删除的 category 主键
     */
    private void deleteChildren(Category category, List<Long> deletes) {
        deletes.add(category.getCategoryId());

        if (hasThirdLevel(category.getCategoryLevel())) {
            return;
        }
        // 如果长度为 1，则代表这是第一次进入。而处在这个位置代表删除这里的元素会改变 category_option 中的元素。
        // 所以这里将 category_option 的缓存删除
        if (deletes.size() == 1) {
            redisUtils.deleteKey("category_option");
        }

        List<Category> childrenCategories = getCategoriesByParentId(category.getCategoryId());
        childrenCategories.forEach(childrenCategory -> deleteChildren(childrenCategory, deletes));
    }

    private List<Category> getCategoriesByParentId(Long id) {
        QueryWrapper<Category> searchChildren = usingIdGetQueryMapperForParentId(id);
        return categoryMapper.selectList(searchChildren);
    }

    private QueryWrapper<Category> usingIdGetQueryMapperForParentId(Long categoryId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", categoryId);
        return queryWrapper;
    }

    @Override
    public Result option() throws JsonProcessingException {
        String categoryOption = redisUtils.getValueForString("category_option");
        if (categoryOption != null) {
            return Result.ok("查询成功", jsonUtils.toJson(categoryOption));
        }

        QueryWrapper<Category> query = new QueryWrapper<>();
        query.ne("category_level", THIRD_LEVEL)
                .orderByAsc("category_level", "parent_id")
                .orderByDesc("category_rank");
        List<Category> categories = categoryMapper.selectList(query);

        if (categories == null) {
            return Result.ok();
        }
        List<Option> optionsTree = dealDataForTree(categories);
        List<Option> root = createRootByOptionsTree(optionsTree);
        redisUtils.storeObjectAsJson("category_option", root);

        return Result.ok(root);
    }

    private List<Option> createRootByOptionsTree(List<Option> firstOptions) throws JsonProcessingException {
        Option rootOption = createRootOption(firstOptions);

        List<Option> rootList = new ArrayList<>();
        rootList.add(rootOption);
        return rootList;
    }

    private Option createRootOption(List<Option> firstOptions) throws JsonProcessingException {
        Map<String, String> idAndParentId = new HashMap<>(4);
        idAndParentId.put("categoryId", "0");
        idAndParentId.put("parentId", "-1");
        String rootValue = jsonUtils.objectToJsonString(idAndParentId);

        return new Option(rootValue, ROOT_CATEGORY_NAME, firstOptions);
    }

    private List<Option> dealDataForTree(List<Category> categories) {
        List<Option> optionsTree = new ArrayList<>();
        Map<Long, List<Option>> optionMap = new HashMap<>(16);
        Map<String, String> idAndParentId = new HashMap<>(4);

        categories.forEach(category -> {
            idAndParentId.put("categoryId", String.valueOf(category.getCategoryId()));
            idAndParentId.put("parentId", String.valueOf(category.getParentId()));
            String valueString = null;
            try {
                valueString = jsonUtils.objectToJsonString(idAndParentId);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Option option = new Option(valueString, category.getCategoryName(), null);

            if (hasFirstLevel(category.getCategoryLevel())) {
                List<Option> options = new ArrayList<>();
                option.setChildren(options);
                optionsTree.add(option);
                optionMap.put(category.getCategoryId(), options);
            } else if (hasSecondLevel(category.getCategoryLevel())) {
                List<Option> options = optionMap.get(category.getParentId());
                options.add(option);
            }
        });

        return optionsTree;
    }

    private boolean hasSecondLevel(Byte level) {
        return SECOND_LEVEL.equals(level);
    }
}
