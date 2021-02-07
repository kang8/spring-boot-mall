package com.kang.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yikang
 * ClassName: JsonUtils
 * Create Date: 2021/2/3 17:06
 */
@Component
public class JsonUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public JsonNode toJson(String str) throws JsonProcessingException {
        return objectMapper.readTree(str);
    }

    public String objectToJsonString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}