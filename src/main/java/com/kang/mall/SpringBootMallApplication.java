package com.kang.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kang
 */
@SpringBootApplication
@MapperScan("com.kang.mall.mapper")
public class SpringBootMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMallApplication.class, args);
    }

}
