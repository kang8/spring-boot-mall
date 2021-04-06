package com.kang.mall.service.mall;

import com.kang.mall.entity.User;
import com.kang.mall.param.mall.LoginParam;

/**
 * @author kang
 * ClassName: LoginService
 * Create Date: 2021/3/14 18:46
 */
public interface LoginService {
    /**
     * 用户登陆
     *
     * @param loginParam 登陆入参
     * @return 是否登陆成功
     */
    User login(LoginParam loginParam);

    /**
     * 注册一个新用户
     *
     * @param loginParam 注册入参
     * @return 是否注册成功了
     */
    Boolean register(LoginParam loginParam);

    /**
     * 登出
     *
     * @return 是否登出成功
     */
    Boolean logout();
}
