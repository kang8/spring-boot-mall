package com.kang.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * @author yikang
 * ClassName: EncodeConfig
 * Description: 密码加密配置类
 * Create Date: 2021/2/8 11:50
 */
@Configuration
public class PasswordEncodeConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13, new SecureRandom());
    }
}
