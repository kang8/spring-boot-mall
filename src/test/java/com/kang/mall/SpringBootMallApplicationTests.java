package com.kang.mall;

import com.kang.mall.entity.AdminUser;
import com.kang.mall.mapper.AdminUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@SpringBootTest
class SpringBootMallApplicationTests {
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testStringRedis() {
        System.out.println(stringRedisTemplate.opsForValue().get("key"));
    }

    @Test
    void testSerializableRedis() {
        AdminUser adminUser = adminUserMapper.selectById(1);
        System.out.println(adminUser);
        System.out.println();
        serializableRedisTemplate.opsForValue().set("admin", adminUser);
        AdminUser admin = (AdminUser) serializableRedisTemplate.opsForValue().get("admin");
        System.out.println(admin);

    }

}
