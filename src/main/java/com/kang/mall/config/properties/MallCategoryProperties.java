package com.kang.mall.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yikang
 * ClassName: MallCategoryProperties
 * Description: 分类属性参数配置类
 * Create Date: 2021/2/7 11:03
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mall.category")
public class MallCategoryProperties {
    private Byte level = 3;
}
