package com.kang.mall.controller.admin;

import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.service.admin.LoginService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: LoginController
 * Create Date: 2021/1/15 20:55
 */
@RestController("LoginAdminController")
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public Result<AdminUser> login(@RequestBody @Valid LoginParam loginParam) {
        AdminUser adminUser = loginService.login(loginParam);
        return ObjectUtils.isEmpty(adminUser) ?
                Result.error("登陆失败", null) :
                Result.ok("登陆成功", adminUser);
    }

    @PostMapping("/logout")
    public Result logout() {
        return loginService.logout() ? Result.ok("成功退出") : Result.error("退出失败");
    }
}
