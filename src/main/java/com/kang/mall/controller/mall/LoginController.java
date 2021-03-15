package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import com.kang.mall.param.mall.LoginParam;
import com.kang.mall.service.mall.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    private HttpSession session;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginParam loginParam) {
        Boolean isLogin = loginService.login(loginParam);
        return isLogin ? Result.ok("登陆成功") : Result.error("登陆失败");
    }

    @PostMapping("/logout")
    public Result logout() {
        try {
            session.removeAttribute("mallLoginId");
            return Result.ok("成功退出");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("退出失败");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid LoginParam loginParam) {
        Boolean isRegister = loginService.register(loginParam);
        return isRegister ? Result.ok("注册成功") : Result.error("注册失败");
    }
}
