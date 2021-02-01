package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.param.admin.GoodsCategoryParam;
import com.kang.mall.service.admin.GoodsCategoryService;
import com.kang.mall.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: GoodsCategoryController
 * Create Date: 2021/2/1 16:35
 */
@RestController
@RequestMapping("/admin")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService categoryService;

    @GetMapping("/category")
    public Result list() {
        return categoryService.list();
    }

    @GetMapping("category/{id}")
    public Result get(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @PostMapping("/category")
    public Result create(@RequestBody @Valid GoodsCategoryParam categoryParam, HttpSession session) {
        Long userId = CommonUtil.getUserId(session, "adminLoginId");
        categoryParam.setCreateUser(userId);
        categoryParam.setUpdateUser(userId);
        return categoryService.create(categoryParam);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable Long id,
                         @RequestBody @Valid GoodsCategoryParam categoryParam,
                         HttpSession session) {
        Long userId = CommonUtil.getUserId(session, "adminLoginId");
        categoryParam.setUpdateUser(userId);
        return categoryService.update(id, categoryParam);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long id) {
        return categoryService.remove(id);
    }


}
