package com.kang.mall.interceptor;

import com.kang.mall.common.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isLogin(request)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    private boolean isLogin(HttpServletRequest request) {
        Object isAdminLogin = request.getSession().getAttribute(Constants.ADMIN_LOGIN_CREDENTIAL);
        Object isMallLogin = request.getSession().getAttribute(Constants.MALL_LOGIN_CREDENTIAL);

        return ObjectUtils.isNotEmpty(isAdminLogin) ||
                ObjectUtils.isNotEmpty(isMallLogin);
    }
}
