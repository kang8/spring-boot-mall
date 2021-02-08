package com.kang.mall.service.admin;


import com.kang.mall.common.Result;
import com.kang.mall.param.admin.LoginParam;

import javax.servlet.http.HttpSession;

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
     * @param session 当前登陆的会话
     * @return AdminUser
     */
    Result login(LoginParam loginParam, HttpSession session);
}
