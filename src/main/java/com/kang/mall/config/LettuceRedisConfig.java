package com.kang.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author yikang
 * ClassName: LettuceRedisConfig
 * Description: Redis 配置类，lettuce 是 redis 的实现包
 * Create Date: 2021/2/3 13:55
 */
@Configuration
@EnableTransactionManagement
public class LettuceRedisConfig {

    @Bean
    public RedisTemplate stringRedisTemplate(LettuceConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        stringRedisTemplate.setEnableTransactionSupport(false);
        return stringRedisTemplate;
    }


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
