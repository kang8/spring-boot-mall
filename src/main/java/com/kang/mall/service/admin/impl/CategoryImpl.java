package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.common.Result;
import com.kang.mall.config.properties.MallCategoryProperties;
import com.kang.mall.entity.Category;
import com.kang.mall.mapper.CategoryMapper;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.pojo.BaseCategory;
import com.kang.mall.result.category.Option;
import com.kang.mall.service.admin.CategoryService;
import com.kang.mall.util.CategoryUtils;
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

    @Autowired
    private CategoryUtils categoryUtils;

    @Autowired
    private MallCategoryProperties categoryProperties;

    @Override
    public Result list() {
        if (redisUtils.hasKey(CATEGORY_LIST)) {
            return Result.ok("查询成功", redisUtils.get(CATEGORY_LIST));
        }

        List<Category> categories = getCategoriesByOrder();
        if (categories == null) {
            return Result.ok();
        }

        List categoriesTree = recursionGetTree(categories, ROOT_CATEGORY_ID);
        redisUtils.set(CATEGORY_LIST, categoriesTree);

        return Result.ok(categoriesTree);
    }

    private List recursionGetTree(List<? extends BaseCategory> baseCategories, Long parentId) {
        List<BaseCategory> children = new ArrayList<>(10);

        for (BaseCategory category : baseCategories) {
            if (parentId.equals(category.getCategoryId())) {
                children = category.getChildren();
            } else if (parentId.equals(category.getParentId())) {
                children.add(category);
                category.setChildren(new ArrayList<>(10));
            }
        }

        if (children.size() == 0) {
            return null;
        }
        children.forEach(category -> recursionGetTree(baseCategories, category.getCategoryId()));
        return children;
    }

    private List<Category> getCategoriesByOrder() {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query
                .orderByAsc("category_level", "parent_id")
                .orderByDesc("category_rank")
                .select("category_id", "category_level", "parent_id", "category_name", "category_rank", "create_time");

        return categoryMapper.selectList(query);
    }

    @Override
    public Result get(Long id) {
        Category category = categoryMapper.selectById(id);
        return category != null ?
                Result.ok("查询成功", category) :
                Result.error("查询失败");
    }

    @Override
    @Transactional
    public Result create(CategoryParam categoryParam) {
        Category category = ClassUtils.copyProperties(categoryParam, new Category());
        category.setCategoryLevel((byte) (categoryParam.getParentLevel() + 1));
        int isInsert = categoryMapper.insert(category);

        cleanCacheByParentId(category.getParentId());

        return isInsert > 0 ?
                Result.ok("添加成功", category) :
                Result.error("添加失败");
    }

    private void cleanCacheByParentId(Long parentId) {
        redisUtils.del(CATEGORY_LIST);
        Category parentCategory = categoryMapper.selectById(parentId);
        if (parentCategory == null || !THIRD_LEVEL.equals(parentCategory.getCategoryLevel())) {
            redisUtils.del(CATEGORY_OPTION);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(Long id, CategoryParam categoryParam) {
        if (categoryParam.getParentId().equals(id)) {
            return Result.error("父节点不能选择标签");
        }

        Category category = categoryMapper.selectById(id);
        BeanUtils.copyProperties(categoryParam, category, "createUser", "createTime");
        category.setUpdateTime(LocalDateTime.now());
        category.setCategoryLevel((byte) (categoryParam.getParentLevel() + 1));
        int isUpdate = categoryMapper.updateById(category);

        cleanCacheByParentId(category.getParentId());

        return isUpdate > 0 ?
                Result.ok("更新成功", category) :
                Result.error("更新失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result remove(Long id) {
        Category category = categoryMapper.selectById(id);

        if (category == null) {
            return Result.error("没有该分类");
        }

        List<Long> deleteArray = new LinkedList<>();
        deleteChildren(category, deleteArray);
        categoryMapper.deleteBatchIds(deleteArray);
        redisUtils.del(CATEGORY_LIST);

        return Result.ok("删除成功");
    }

    /**
     * 使用递归来查找要删除的 category 主键
     */
    private void deleteChildren(Category category, List<Long> deletes) {
        deletes.add(category.getCategoryId());

        if (categoryUtils.hasThirdLevel(category.getCategoryLevel())) {
            return;
        }
        // 如果长度为 1，则代表这是第一次进入。而处在这个位置代表删除这里的元素会改变 category_option 中的元素。
        // 所以这里将 category_option 的缓存删除
        if (deletes.size() == 1) {
            redisUtils.del(CATEGORY_OPTION);
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
        if (redisUtils.hasKey(CATEGORY_OPTION)) {
            return Result.ok("查询成功", redisUtils.get(CATEGORY_OPTION));
        }

        List<Category> categories = getCategoriesByOption();

        Category root = new Category(ROOT_CATEGORY_ID, ROOT_LEVEL, ROOT_PARENT_ID, ROOT_CATEGORY_NAME);
        // time complexity O(n)
        categories.add(0, root);

        List<Option> options = toOption(categories);

        List rootOption = recursionGetTree(options, ROOT_PARENT_ID);
        redisUtils.set(CATEGORY_OPTION, rootOption);

        return Result.ok(rootOption);
    }

    private List<Category> getCategoriesByOption() {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.ne("category_level", categoryProperties.getLevel())
                .orderByAsc("category_level", "parent_id")
                .orderByDesc("category_rank")
                .select("category_id", "category_level", "parent_id", "category_name");
        return categoryMapper.selectList(query);
    }

    private List<Option> toOption(List<Category> categories) throws JsonProcessingException {
        List<Option> options = new ArrayList<>();
        Map<String, String> value = new HashMap<>(4);
        for (Category category : categories) {
            Option option = ClassUtils.copyProperties(category, new Option());

            value.put("categoryId", String.valueOf(category.getCategoryId()));
            value.put("categoryLevel", String.valueOf(category.getCategoryLevel()));
            value.put("parentId", String.valueOf(category.getParentId()));
            String valueOfJson = jsonUtils.objectToJsonString(value);
            option.setValue(valueOfJson);
            options.add(option);
        }
        return options;
    }

    public void testTreeList() throws JsonProcessingException {
        List<Category> categoriesByOrder = getCategoriesByOrder();
        List list = recursionGetTree(categoriesByOrder, ROOT_CATEGORY_ID);
        String s = jsonUtils.objectToJsonString(list);
        System.out.println(s);
    }

    public void testTreeOption() throws JsonProcessingException {
        List<Category> categories = getCategoriesByOption();
        Category root = new Category(ROOT_CATEGORY_ID, ROOT_LEVEL, ROOT_PARENT_ID, ROOT_CATEGORY_NAME);
        // time complexity O(n)
        categories.add(0, root);
        List<Option> options = toOption(categories);

        List rootOptions = recursionGetTree(options, ROOT_PARENT_ID);
        String s = jsonUtils.objectToJsonString(rootOptions);
        System.out.println(s);
    }
}
