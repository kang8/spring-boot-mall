package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.User;
import com.kang.mall.mapper.UserMapper;
import com.kang.mall.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Page<User> list(Integer page, Integer size) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("user_id", "username", "phone", "address", "is_locked", "create_time");
        return userMapper.selectPage(new Page<>(page, size), query);
    }
}
