package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.service.admin.LoginService;
import com.kang.mall.util.CommonUtils;
import com.kang.mall.util.RedisUtils;
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
@Service("LoginAdminService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public AdminUser login(LoginParam loginParam) {
        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("username", loginParam.getUsername())
                .select("username", "admin_user_id", "nick_name", "password");
        AdminUser adminUser = adminUserMapper.selectOne(query);
        if (ObjectUtils.isEmpty(adminUser)) {
            throw new CustomizeException("该用户不存在");
        }
        boolean isMatch = passwordEncoder.matches(loginParam.getPassword(), adminUser.getPassword());
        if (isMatch) {
            session.setAttribute(Constants.ADMIN_LOGIN_CREDENTIAL, adminUser.getAdminUserId());
            adminUser.setPassword(null);

            redisUtils.set("login_adminUser_" + adminUser.getAdminUserId(), adminUser);
            return adminUser;
        }
        throw new CustomizeException("密码错误");
    }

    @Override
    public Boolean logout() {
        try {
            Long adminUserId = CommonUtils.getAdminUserId(session);
            redisUtils.del("login_adminUser_" + adminUserId);

            session.removeAttribute(Constants.ADMIN_LOGIN_CREDENTIAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
