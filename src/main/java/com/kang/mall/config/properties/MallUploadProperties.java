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
    /**
     * 文件上传后指定的本地文件夹路径
     */
    private String directory;
    /**
     * 访问 directory 文件里的静态资源，为一个数组。
     * 如 {"upload", "image"}
     * 表示既可以访问 /upload 也可以访问 /image
     */
    private String[] path = {"upload"};
}
