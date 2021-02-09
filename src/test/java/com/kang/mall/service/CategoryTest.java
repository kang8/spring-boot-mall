package com.kang.mall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.mapper.CategoryMapper;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.service.admin.impl.CategoryImpl;
import com.kang.mall.util.ClassUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yikang
 * ClassName: CategoryTest
 * Create Date: 2021/2/7 11:34
 */
@SpringBootTest
public class CategoryTest {
    @Autowired
    private CategoryImpl category;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testGetTreeList() throws Exception {
        category.testTreeList();
    }

    @Test
    public void testGetTreeOption() throws JsonProcessingException {
        category.testTreeOption();
    }

    @Test
    void testValidaParent() {
        CategoryParam firstLevel = ClassUtils.copyProperties(categoryMapper.selectById(1L), new CategoryParam());
        firstLevel.setParentLevel((byte) 1);
        CategoryParam secondLevel = ClassUtils.copyProperties(categoryMapper.selectById(19L), new CategoryParam());
        secondLevel.setParentLevel((byte) 2);
        CategoryParam thirdLevel = ClassUtils.copyProperties(categoryMapper.selectById(92L), new CategoryParam());
        thirdLevel.setParentLevel((byte) 3);
        // 4 deep is 3
        Assertions.assertFalse(this.category.testValidateParent(4L, firstLevel));
        Assertions.assertFalse(this.category.testValidateParent(4L, secondLevel));
        Assertions.assertFalse(this.category.testValidateParent(4L, thirdLevel));
        // 15 deep is 2
        Assertions.assertTrue(this.category.testValidateParent(11L, firstLevel));
        Assertions.assertFalse(this.category.testValidateParent(11L, secondLevel));
        Assertions.assertFalse(this.category.testValidateParent(11L, thirdLevel));
        // 50 deep is 1
        Assertions.assertTrue(this.category.testValidateParent(50L, firstLevel));
        Assertions.assertTrue(this.category.testValidateParent(50L, secondLevel));
        Assertions.assertFalse(this.category.testValidateParent(50L, thirdLevel));
    }

}
