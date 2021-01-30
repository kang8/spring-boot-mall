package com.kang.mall.service.admin;


import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.LoginParam;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;
import com.kang.mall.vo.AdminUserVO;

/**
 * @author yikang
 * ClassName: LoginService
 * Create Date: 2021/1/20 15:06
 */
public interface LoginService {
    /**
     * 登陆验证
     *
     * @param loginParam 登陆入参
     * @return AdminUser
     */
    AdminUser login(LoginParam loginParam);

    /**
     * 更新用户名与别名
     *
     * @param param 名字入参
     * @return AdminUserVo
     */
    Result<AdminUserVO> updateName(NameParam param);

    Result updatePassword(PasswordParam param);
}
