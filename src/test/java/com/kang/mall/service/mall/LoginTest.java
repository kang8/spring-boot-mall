package com.kang.mall.service.mall;

import com.kang.mall.param.mall.LoginParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kang
 * ClassName: LoginTest
 * Create Date: 2021/3/15 16:30
 */
@SpringBootTest
public class LoginTest {
    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin() {
        LoginParam login = new LoginParam();
        login.setPassword("yikang");
        login.setPhone("18012344321");
        System.out.println(loginService.login(login));
    }

    @Test
    public void testSamePhoneToRegister() {
        LoginParam login = new LoginParam();
        login.setPassword("yikang");
        login.setPhone("18012344321");
        loginService.register(login);
    }

    @Test
    public void testRegister() {
        LoginParam login = new LoginParam();
        login.setPassword("yikang");
        login.setPhone("321");
        loginService.register(login);
    }

}
