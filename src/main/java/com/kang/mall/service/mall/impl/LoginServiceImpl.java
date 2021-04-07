package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.entity.User;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.UserMapper;
import com.kang.mall.param.mall.LoginParam;
import com.kang.mall.service.mall.LoginService;
import com.kang.mall.util.ClassUtils;
import com.kang.mall.util.CommonUtils;
import com.kang.mall.util.RedisUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author kang
 * ClassName: LoginServiceImpl
 * Create Date: 2021/3/14 18:46
 */
@Service

public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public User login(LoginParam loginParam) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("phone", loginParam.getPhone())
                .select("username", "password", "address", "user_id", "phone", "create_time");
        User user = userMapper.selectOne(query);

        if (ObjectUtils.isEmpty(user)) {
            throw new CustomizeException("该用户不存在");
        }

        boolean isMatch = passwordEncoder.matches(loginParam.getPassword(), user.getPassword());
        if (isMatch) {
            session.setAttribute(Constants.MALL_LOGIN_CREDENTIAL, user.getUserId());
            user.setPassword(null);

            redisUtils.set("login_user_" + user.getUserId(), user);
            return user;
        } else {
            throw new CustomizeException("密码错误");
        }
    }

    @Override
    public Boolean register(LoginParam loginParam) {
        CommonUtils.validatePhoneNumberNotPassThrowException(loginParam.getPhone());

        String encodePassword = passwordEncoder.encode(loginParam.getPassword());
        loginParam.setPassword(encodePassword);

        User user = ClassUtils.copyProperties(loginParam, new User());
        user.setUsername("user_" + user.getPhone());
        try {
            return userMapper.insert(user) > 0;
        } catch (DuplicateKeyException e) {
            throw new CustomizeException("该手机号已存在");
        }
    }

    @Override
    public Boolean logout() {
        try {
            Long userId = CommonUtils.getUserId(session);
            redisUtils.del("login_user_" + userId);

            session.removeAttribute(Constants.MALL_LOGIN_CREDENTIAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
