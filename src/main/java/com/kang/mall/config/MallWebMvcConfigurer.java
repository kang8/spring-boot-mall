package com.kang.mall.config;

import com.kang.mall.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yikang
 * ClassName: MallWebMvcConfigurer
 * Description: 拦截配置类
 * Create Date: 2021/1/19 20:29
 */
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {
    @Value("${mall.upload.directory}")
    private String fileUploadPath;

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/upload")
                .excludePathPatterns("/admin/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /upload 上的请求转发到 F:\\upload 上.
        // 如请求 /upload/123.png 就相当于请求 F:\\upload\123.png
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + fileUploadPath);
    }
}