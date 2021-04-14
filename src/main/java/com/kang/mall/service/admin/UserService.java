package com.kang.mall.service.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.mall.entity.User;

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
}
