package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.User;
import com.kang.mall.mapper.UserMapper;
import com.kang.mall.param.admin.UserParam;
import com.kang.mall.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author kang
 * ClassName: UserServiceImpl
 * Create Date: 2021/3/14 18:40
 */
@Service("User_AdminService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> list(Integer page, Integer size) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("user_id", "username", "phone", "address", "is_locked", "create_time");
        return userMapper.selectPage(new Page<>(page, size), query);
    }

    @Override
    public boolean resetPassword(Long id) {
        User user = initUser(id);
        int isUpdate = userMapper.updateById(user);
        return isUpdate > 0;
    }

    private User initUser(Long id) {
        User user = new User();
        user.setUserId(id);
        user.setPassword(passwordEncoder.encode("123456"));
        return user;
    }

    @Override
    public boolean update(Long id, UserParam userParam) {
        User user = initUser(id, userParam);
        int isUpdate = userMapper.updateById(user);
        return isUpdate > 0;
    }

    private User initUser(Long id, UserParam userParam) {
        User user = new User();
        user.setUserId(id);
        user.setIsLocked(userParam.getIsLocked());
        return user;
    }
}
