package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.AdminUserParam;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;
import com.kang.mall.service.admin.AdminUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: AdminUserController
 * Create Date: 2021/1/25 15:42
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/admin-user")
    public Result list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return adminUserService.list(page, size);
    }

    @RequestMapping(value = "/profile/name", method = RequestMethod.PUT)
    public Result updateName(@RequestBody @Valid NameParam nameParam) {
        return adminUserService.updateName(nameParam);
    }

    @PostMapping(value = "/admin-user")
    public Result<AdminUser> create(@RequestBody @Valid AdminUserParam adminUserParam) {
        AdminUser adminUser = adminUserService.create(adminUserParam);
        if (ObjectUtils.isNotEmpty(adminUser)) {
            return Result.ok(adminUser);
        }
        return Result.error();
    }

    @RequestMapping(value = "/admin-user/{id}/password", method = RequestMethod.PUT)
    public Result resetPassword(@PathVariable Long id) {
        return adminUserService.resetPassword(id) ? Result.ok("重置密码成功，密码重置为 123456") : Result.error("重置密码失败");
    }

    @RequestMapping(value = "/profile/password", method = RequestMethod.PUT)
    public Result updatePassword(@RequestBody @Valid PasswordParam passwordParam) {
        return adminUserService.updatePassword(passwordParam);
    }
}
