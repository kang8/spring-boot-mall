package com.kang.mall.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.User;
import com.kang.mall.param.admin.UserParam;
import com.kang.mall.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kang
 * ClassName: UserController
 * Create Date: 2021/3/14 18:15
 */
@RestController("User_AdminController")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Result<Page<User>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Page<User> list = userService.list(page, size);
        return Result.ok(list);
    }

    @GetMapping("/user/{id}")
    public Result get(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/user")
    public Result create() {
        return null;
    }

    @RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
    public Result resetPassword(@PathVariable Long id) {
        return userService.resetPassword(id) ? Result.ok("重置密码成功，密码重置为 123456") : Result.error("重置密码失败");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable Long id, @RequestBody @Valid UserParam userParam) {
        return userService.update(id, userParam) ? Result.ok("更新成功") : Result.error("更新失败");
    }

    @RequestMapping(value = "/user{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long id) {
        return null;
    }
}
