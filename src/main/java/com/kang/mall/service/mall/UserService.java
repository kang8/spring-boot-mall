package com.kang.mall.service.mall;

import com.kang.mall.entity.User;
import com.kang.mall.param.mall.UserInfoParam;
import com.kang.mall.param.mall.UserPasswordParam;

/**
 * @author kang
 * ClassName: UserService
 * Create Date: 2021/3/14 17:47
 */
public interface UserService {
    /**
     * 根据用户 id 获取用户信息
     *
     * @param id 用户 id
     * @return User
     */
    User get(Long id);

    /**
     * 根据用户 id 和传来的用户信息修改用户信息
     *
     * @param id 用户 id
     * @param userInfoParam
     * @return boolean
     */
    boolean updateBaseInfo(Long id, UserInfoParam userInfoParam);

    /**
     * 根据用户 id 和传来的密码，修改用户密码
     *
     * @param id 用户 id
     * @param userPasswordParam
     * @return boolean
     */
    boolean updatePassword(Long id, UserPasswordParam userPasswordParam);
}
