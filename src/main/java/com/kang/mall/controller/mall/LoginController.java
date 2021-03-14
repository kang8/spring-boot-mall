package com.kang.mall.controller.mall;

import com.kang.mall.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kang
 * ClassName: LoginController
 * Create Date: 2021/3/14 18:47
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public Result login() {
        return null;
    }

    @PostMapping("/logout")
    public Result logout() {
        return null;
    }

    @PostMapping("/register")
    public Result register() {
        return null;
    }
}
