package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yikang
 * ClassName: CategoryController
 * Create Date: 2021/3/7 18:30
 */
@RestController("/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/option")
    public Result option() {
        return categoryService.option();
    }
}
