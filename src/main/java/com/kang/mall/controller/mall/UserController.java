package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.entity.User;
import com.kang.mall.param.mall.UserInfoParam;
import com.kang.mall.param.mall.UserPasswordParam;
import com.kang.mall.service.mall.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kang
 * ClassName: UserController
 * Create Date: 2021/3/14 17:46
 */
@RestController
@RequestMapping("/mall")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Result<User> get(@PathVariable("id") Long id) {
        User user = userService.get(id);
        return ObjectUtils.isNotEmpty(user) ? Result.ok(user) : Result.error("没有找到该用户");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result updateBaseInfo(@PathVariable("id") Long id, @RequestBody @Valid UserInfoParam userInfoParam) {
        return userService.updateBaseInfo(id, userInfoParam) ? Result.ok("更新成功") : Result.error("更新失败");
    }

    @RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
    public Result updatePassword(@PathVariable("id") Long id, @RequestBody @Valid UserPasswordParam userPasswordParam) {
        return userService.updatePassword(id, userPasswordParam) ? Result.ok("更新密码成功") : Result.error("更新密码失败");
    }
}
