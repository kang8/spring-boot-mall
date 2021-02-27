package com.kang.mall.config;

import com.kang.mall.config.properties.MallUploadProperties;
import com.kang.mall.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yikang
 * ClassName: MallWebMvcConfigurer
 * Description: 商城 MVC 配置类
 * Create Date: 2021/1/19 20:29
 */
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private MallUploadProperties upload;

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
        String[] paths = upload.getPath();
        String[] pathPatterns = new String[paths.length];
        for (int i = 0; i < paths.length; i++) {
            pathPatterns[i] = "/" + paths[i] + "/**";
        }
        // 假设 pathPatterns[0] = "/upload/**
        // 下面这条语句的作用为将 /upload 上的请求转发到 F:\\upload 上.
        // 如请求 /upload/123.png 就相当于请求 F:\\upload\123.png
        registry.addResourceHandler(pathPatterns).addResourceLocations("file:" + upload.getDirectory());
    }
}
