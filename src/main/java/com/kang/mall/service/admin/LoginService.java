package com.kang.mall.service.admin;


import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.LoginParam;

/**
 * @author yikang
 * ClassName: LoginService
 * Create Date: 2021/1/20 15:06
 */
public interface LoginService {
    /**
     * 登陆验证
     *
     * @param loginParam 登陆入参
     * @return AdminUser
     */
    AdminUser login(LoginParam loginParam);
}
