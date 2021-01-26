package com.kang.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.mapper.AdminUserMapper;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;
import com.kang.mall.service.admin.LoginService;
import com.kang.mall.util.ClassUtil;
import com.kang.mall.util.MD5Util;
import com.kang.mall.vo.AdminUserVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yikang
 * ClassName: LoginServiceImpl
 * Description:
 * Create Date: 2021/1/20 15:19
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(LoginParam loginParam) {
        String hashPassword = MD5Util.MD5Encode(loginParam.getUsername(), loginParam.getPassword());

        // TODO: 根据不同的状态返回不同的消息。是没有这个用户还是密码错误

        QueryWrapper<AdminUser> query = new QueryWrapper<>();
        query.eq("username", loginParam.getUsername()).eq("password", hashPassword);
        return adminUserMapper.selectOne(query);
    }

    @Override
    public Result<AdminUserVO> updateName(NameParam param) {
        AdminUser user = adminUserMapper.selectById(param.getAdminUserId());

        if (ObjectUtils.isNotEmpty(user)) {
            user.setUsername(param.getUsername());
            user.setNickName(param.getNickName());

            return adminUserMapper.updateById(user) == 0 ? Result.error("更新失败") :
                    Result.ok(ClassUtil.copyProperties(user, new AdminUserVO()));
        } else {
            return Result.error("没有找到这个用户");
        }
    }

    @Override
    public Result updatePassword(PasswordParam param) {
        AdminUser user = adminUserMapper.selectById(param.getAdminUserId());

        String databasePass = MD5Util.MD5Encode(user.getUsername(), param.getOriginPassword());

        if (databasePass.equals(user.getPassword())) {
            String newDatabasePass = MD5Util.MD5Encode(user.getUsername(), param.getNewPassword());
            user.setPassword(newDatabasePass);

            return adminUserMapper.updateById(user) == 0 ? Result.error("更新失败") :
                    Result.ok("更新成功");
        } else {
            return Result.error("密码错误");
        }
    }
}
