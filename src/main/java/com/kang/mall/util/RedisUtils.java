package com.kang.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yikang
 * ClassName: RedisUtils
 * Create Date: 2021/2/3 16:54
 */
@Component
public class RedisUtils {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void storeObjectAsJson(String key, Object value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        stringRedisTemplate.opsForValue().set(key, json);
    }

    public String getValueForString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
