package com.kang.mall.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yikang
 * ClassName: PasswordEncodeTest
 * Create Date: 2021/2/8 13:49
 */
@SpringBootTest
public class PasswordEncodeTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testBcrypt() {
        String password = passwordEncoder.encode("yikang");
        System.out.println(password);
        boolean matches = passwordEncoder.matches("yikang", password);
        Assertions.assertTrue(matches);
    }

    @Test
    void generatePassword() {
        String password = passwordEncoder.encode("yikang");
        System.out.println(password);

        for (int i = 1; i < 80; i++) {
            String temp = "test" + i;
            String testPassword = passwordEncoder.encode("123456");
            System.out.println("(\"" + temp + "\", \"" + testPassword + "\" ,\"" + temp + "\"),");
        }
    }
}
