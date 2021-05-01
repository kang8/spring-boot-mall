package com.kang.mall.service.admin;

import com.kang.mall.common.Result;
import com.kang.mall.entity.AdminUser;
import com.kang.mall.param.admin.AdminUserParam;
import com.kang.mall.param.admin.profile.NameParam;
import com.kang.mall.param.admin.profile.PasswordParam;

/**
 * @author yikang
 * ClassName: AdminUserService
 * Create Date: 2021/1/30 16:27
 */
public interface AdminUserService {

    /**
     * 根据分页数据，查询 Admin User 表
     *
     * @param page 页码
     * @param size 单页长度
     * @return Result
     */
    Result list(Integer page, Integer size);

    /**
     * 更新用户名与别名
     *
     * @param nameParam 名字入参
     * @return Result
     */
    Result updateName(NameParam nameParam);

    /**
     * 更新管理员密码
     *
     * @param passwordParam 密码入参，传入新密码和旧密码
     * @return Result
     */
    Result updatePassword(PasswordParam passwordParam);

    /**
     * 根据前端传来的参数创建用户
     *
     * @param adminUserParam 前端传来的名字参数
     * @return
     */
    AdminUser create(AdminUserParam adminUserParam);

    /**
     * 根据管理员 id 号更新密码
     *
     * @param id 管理员 id 号
     * @return
     */
    boolean resetPassword(Long id);

    /**
     * 删除指定 id 号的用户
     *
     * @param id
     * @return
     */
    boolean remove(Long id);
}
