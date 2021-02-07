package com.kang.mall.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yikang
 * ClassName: MallUploadProperties
 * Description: 上传属性参数配置类
 * Create Date: 2021/2/7 11:02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mall.upload")
public class MallUploadProperties {
    private String directory;
}
