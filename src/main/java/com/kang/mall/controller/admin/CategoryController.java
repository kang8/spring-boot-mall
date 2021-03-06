package com.kang.mall.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kang.mall.common.Result;
import com.kang.mall.param.admin.CategoryParam;
import com.kang.mall.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: CategoryController
 * Create Date: 2021/2/1 16:35
 */
@RestController("CategoryAdminController")
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public Result list() {
        return categoryService.list();
    }

    @GetMapping("category/{id}")
    public Result get(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @PostMapping("/category")
    public Result create(@RequestBody @Valid CategoryParam categoryParam) {
        return categoryService.create(categoryParam);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable Long id,
                         @RequestBody @Valid CategoryParam categoryParam) {
        return categoryService.update(id, categoryParam);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long id) {
        return categoryService.remove(id);
    }

    @GetMapping("/category/option/parent")
    public Result optionOfParent() throws JsonProcessingException {
        return categoryService.optionOfParent();
    }

    @GetMapping("/category/option")
    public Result option() {
        return categoryService.option();
    }
}
