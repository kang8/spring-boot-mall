package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.service.admin.LoginService;
import com.kang.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yikang
 * ClassName: LoginServiceImpl
 * Create Date: 2021/1/20 15:19
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(LoginParam loginParam) {
        String hashPassword = MD5Utils.customizeMd5Encode(loginParam.getUsername(), loginParam.getPassword());

        // TODO: 根据不同的状态返回不同的消息。是没有这个用户还是密码错误

        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("username", loginParam.getUsername()).eq("password", hashPassword)
                .select("username", "admin_user_id", "nick_name");
        return adminUserMapper.selectOne(query);
    }
}
