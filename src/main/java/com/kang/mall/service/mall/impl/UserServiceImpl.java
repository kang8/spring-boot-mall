package com.kang.mall.service.mall.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.entity.User;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.UserMapper;
import com.kang.mall.param.mall.UserInfoParam;
import com.kang.mall.param.mall.UserPasswordParam;
import com.kang.mall.service.mall.UserService;
import com.kang.mall.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author kang
 * ClassName: UserServiceImpl
 * Create Date: 2021/3/14 17:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Override
    public User get(Long id) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("user_id", id).select("username", "phone", "address");
        return userMapper.selectOne(query);
    }

    @Override
    public boolean updateBaseInfo(Long id, UserInfoParam userInfoParam) {
        checkPermission(id);

        User user = initUser(id, userInfoParam);
        int update = userMapper.updateById(user);
        return update > 0;
    }

    private User initUser(Long id, UserInfoParam userInfoParam) {
        User user = new User();
        user.setUserId(id);
        user.setUsername(userInfoParam.getUsername());
        user.setAddress(userInfoParam.getAddress());
        return user;
    }

    @Override
    public boolean updatePassword(Long id, UserPasswordParam userPasswordParam) {
        checkPermission(id);

        String password = getPassword(id);

        if (passwordEncoder.matches(userPasswordParam.getOriginalPassword(), password)) {
            User user = initUser(id, userPasswordParam.getNewPassword());
            int update = userMapper.updateById(user);
            return update > 0;
        }
        throw new CustomizeException("原密码错误");
    }

    private void checkPermission(Long id) {
        Long userId = CommonUtils.getUserId(session);
        if (!Objects.equals(id, userId)) {
            throw new CustomizeException("没有权限");
        }
    }

    private User initUser(Long id, String password) {
        User user = new User();
        user.setUserId(id);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    private String getPassword(Long id) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("user_id", id).select("password");
        User user = userMapper.selectOne(query);
        return user.getPassword();
    }
}
