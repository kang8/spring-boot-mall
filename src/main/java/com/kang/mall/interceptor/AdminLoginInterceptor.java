package com.kang.mall.interceptor;

import com.kang.mall.common.Constants;
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
        if (null == request.getSession().getAttribute(Constants.ADMIN_LOGIN_CREDENTIAL)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            return true;
        }
    }
}
