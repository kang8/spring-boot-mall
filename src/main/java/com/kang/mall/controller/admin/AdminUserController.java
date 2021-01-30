package com.kang.mall.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;
import com.kang.mall.service.admin.LoginService;
import com.kang.mall.vo.AdminUserVO;
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
    private AdminUserMapper adminUserMapper;

    @Autowired
    private LoginService loginService;

    @GetMapping("/user")
    public Object list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer limit
    ) {
        Page<AdminUser> pages = new Page<>(page, limit);

        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("locked", 0).select("admin_user_id", "username", "nick_name");

        Page<AdminUser> adminUserPage = adminUserMapper.selectPage(pages, query);

        return Result.ok(adminUserPage);
    }

    @RequestMapping(value = "/profile/name", method = RequestMethod.PUT)
    public Result<AdminUserVO> name(@RequestBody @Valid NameParam param) {
        return loginService.updateName(param);
    }

    @RequestMapping(value = "/profile/password", method = RequestMethod.PUT)
    public Result password(@RequestBody @Valid PasswordParam param) {
        return loginService.updatePassword(param);
    }
}
