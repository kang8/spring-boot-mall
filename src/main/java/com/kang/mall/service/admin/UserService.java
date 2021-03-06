package com.kang.mall.service.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.common.Result;
import com.kang.mall.entity.User;
import com.kang.mall.param.admin.UserParam;

/**
 * @author kang
 * ClassName: UserService
 * Create Date: 2021/3/14 18:40
 */
public interface UserService {
    /**
     * 根据传来的页码和每页数量查询用户人数
     *
     * @param page 页码
     * @param size 每页数量
     * @return Page
     */
    Page<User> list(Integer page, Integer size);

    /**
     * 重置密码
     *
     * @param id 用户 Id
     * @return boolean 是否成功
     */
    boolean resetPassword(Long id);

    /**
     * 根据传来的用户 id 和参数更新用户表中的数据
     *
     * @param id 用户 Id
     * @param userParam
     * @return boolean 是否成功
     */
    boolean update(Long id, UserParam userParam);
}
