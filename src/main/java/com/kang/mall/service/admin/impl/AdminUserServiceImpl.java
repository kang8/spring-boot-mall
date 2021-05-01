package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.exception.CustomizeException;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.AdminUserParam;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;
import com.kang.mall.service.admin.AdminUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yikang
 * ClassName: AdminUserServiceImpl
 * Create Date: 2021/1/30 16:27
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result list(Integer page, Integer size) {

        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("locked", 0).select("admin_user_id", "username", "nick_name");

        Page<AdminUser> adminUserPage = adminUserMapper.selectPage(new Page<AdminUser>(page, size), query);

        return Result.ok(adminUserPage);
    }

    @Override
    public Result updateName(NameParam nameParam) {
        AdminUser user = adminUserMapper.selectById(nameParam.getAdminUserId());
        user.setPassword(null);

        if (ObjectUtils.isNotEmpty(user)) {
            user.setUsername(nameParam.getUsername());
            user.setNickName(nameParam.getNickName());

            int isUpdate = adminUserMapper.updateById(user);
            return isUpdate >= 0 ?
                    Result.ok("更新成功", user) :
                    Result.error("更新失败");
        } else {
            return Result.error("没有找到这个用户");
        }
    }

    @Override
    public Result updatePassword(PasswordParam passwordParam) {
        AdminUser user = adminUserMapper.selectById(passwordParam.getAdminUserId());

        if (ObjectUtils.isEmpty(user)) {
            return Result.error("用户不存在");
        }

        if (!passwordEncoder.matches(passwordParam.getOriginPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }
        user.setPassword(passwordEncoder.encode(passwordParam.getNewPassword()));

        int isUpdate = adminUserMapper.updateById(user);
        return isUpdate >= 0 ?
                Result.ok("更新成功") :
                Result.error("更新失败");
    }

    @Override
    public AdminUser create(AdminUserParam adminUserParam) {
        if (checkUsernameExist(adminUserParam.getUsername())) {
            throw new CustomizeException("登录名称重复了！");
        }

        AdminUser adminUser = initUser(adminUserParam);
        int isInsert = adminUserMapper.insert(adminUser);
        if (isInsert > 0) {
            adminUser.setPassword(null);
            return adminUser;
        }
        return null;
    }

    private boolean checkUsernameExist(String username) {
        AdminUser adminUser = adminUserMapper.selectOne(new QueryWrapper<AdminUser>()
                .lambda().eq(AdminUser::getUsername, username));
        return ObjectUtils.isNotEmpty(adminUser);
    }

    private AdminUser initUser(AdminUserParam adminUserParam) {
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(adminUserParam.getUsername());
        adminUser.setNickName(adminUserParam.getNickName());
        adminUser.setPassword(passwordEncoder.encode("123456"));
        return adminUser;
    }

    @Override
    public boolean resetPassword(Long id) {
        AdminUser adminUser = initUser(id);
        return adminUserMapper.updateById(adminUser) > 0;
    }

    private AdminUser initUser(Long id) {
        AdminUser adminUser = new AdminUser();
        adminUser.setPassword(passwordEncoder.encode("123456"));
        adminUser.setAdminUserId(id);
        return adminUser;
    }
}
