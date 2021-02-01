package com.kang.mall.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yikang
 * ClassName: AdminLoginInterceptor
 * Description: 后台登陆拦截器
 * Create Date: 2021/1/19 20:14
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (checkUriAndSessionValidate(request, uri)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            return true;
        }
    }

    private boolean checkUriAndSessionValidate(HttpServletRequest request, String uri) {
        return (
                uri.startsWith("/admin") || "/upload".equals(uri)
        ) &&
                null == request.getSession().getAttribute("adminLoginId");
    }

}
