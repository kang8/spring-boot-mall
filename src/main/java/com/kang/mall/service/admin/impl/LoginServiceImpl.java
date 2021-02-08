package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.service.admin.LoginService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author yikang
 * ClassName: LoginServiceImpl
 * Create Date: 2021/1/20 15:19
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result login(LoginParam loginParam, HttpSession session) {
        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("username", loginParam.getUsername())
                .select("username", "admin_user_id", "nick_name", "password");
        AdminUser adminUser = adminUserMapper.selectOne(query);
        if (ObjectUtils.isEmpty(adminUser)) {
            return Result.error("该用户不存在");
        }
        boolean isMatch = passwordEncoder.matches(loginParam.getPassword(), adminUser.getPassword());
        if (isMatch) {
            session.setAttribute("adminLoginId", adminUser.getAdminUserId());
            adminUser.setPassword(null);
            return Result.ok("登陆成功", adminUser);
        }

        return Result.error("密码错误");
    }
}
