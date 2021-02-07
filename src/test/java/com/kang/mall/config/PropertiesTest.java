package com.kang.mall.config;

import com.kang.mall.config.properties.MallCategoryProperties;
import com.kang.mall.config.properties.MallUploadProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yikang
 * ClassName: PropertiesTest
 * Description:
 * Create Date: 2021/2/7 11:07
 */
@SpringBootTest
public class PropertiesTest {
    @Autowired
    private MallCategoryProperties category;

    @Autowired
    private MallUploadProperties upload;

    @Test
    public void testMallCategoryProperties() {
        System.out.println(upload.getDirectory());
    }

    @Test
    public void testMallUploadProperties() {
        System.out.println(category.getLevel());
    }
}
