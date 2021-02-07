package com.kang.mall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.service.admin.impl.CategoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yikang
 * ClassName: CategoryText
 * Create Date: 2021/2/7 11:34
 */
@SpringBootTest
public class CategoryText {
    @Autowired
    private CategoryImpl category;

    @Test
    public void testGetTreeList() throws Exception {
        category.testTreeList();
    }

    @Test
    public void testGetTreeOption() throws JsonProcessingException {
        category.testTreeOption();
    }

}
