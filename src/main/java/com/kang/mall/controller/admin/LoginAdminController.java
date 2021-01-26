package com.kang.mall.controller.admin;

import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.service.admin.LoginService;
import com.kang.mall.util.ClassUtil;
import com.kang.mall.vo.AdminUserVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author yikang
 * ClassName: LoginController
 * Description:
 * Create Date: 2021/1/15 20:55
 */
@RestController
@RequestMapping("/admin")
public class LoginAdminController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<AdminUserVO> login(@RequestBody @Valid LoginParam loginParam, HttpSession session) {
        AdminUser user = loginService.login(loginParam);

        if (ObjectUtils.isNotEmpty(user)) {
            session.setAttribute("loginUser", user.getUsername());
            return Result.ok("登陆成功", ClassUtil.copyProperties(user, new AdminUserVO()));
        }
        return Result.error("login fail");
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUser");
        return Result.ok("成功退出");
    }
}
