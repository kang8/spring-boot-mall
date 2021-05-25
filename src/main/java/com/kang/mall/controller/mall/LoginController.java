package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.entity.User;
import com.kang.mall.param.mall.LoginParam;
import com.kang.mall.result.UserResult;
import com.kang.mall.service.mall.CartService;
import com.kang.mall.service.mall.LoginService;
import com.kang.mall.util.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author kang
 * ClassName: LoginController
 * Create Date: 2021/3/14 18:47
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CartService cartService;

    @PostMapping("/login")
    public Result<UserResult> login(@RequestBody @Valid LoginParam loginParam) {
        User user = loginService.login(loginParam);
        if (ObjectUtils.isEmpty(user)) {
            return Result.error("登陆失败", null);
        }
        UserResult userResult = ClassUtils.copyProperties(user, new UserResult());
        userResult.setCartCount(cartService.getTotalCount());

        return Result.ok("登陆成功", userResult);
    }

    @PostMapping("/logout")
    public Result logout() {
        return loginService.logout() ? Result.ok("成功退出") : Result.error("退出失败");
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid LoginParam loginParam) {
        Boolean isRegister = loginService.register(loginParam);
        return isRegister ? Result.ok("注册成功") : Result.error("注册失败");
    }
}
