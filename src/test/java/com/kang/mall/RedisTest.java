package com.kang.mall;

import com.kang.mall.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yikang
 * ClassName: RedisTest
 * Create Date: 2021/2/7 16:37
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedis() {
        boolean isStore = redisUtils.set("hello", "world");
        if (isStore) {
            Object hello = redisUtils.get("hello");
            System.out.println(hello);
        }
        redisUtils.del("hello");
    }
}
