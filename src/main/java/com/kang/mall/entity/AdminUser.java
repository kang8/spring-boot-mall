package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @author yikang
 * ClassName: AdminUser
 * Description: 管理员表
 * Create Date: 2021/1/15 20:49
 */
@Data
public class AdminUser {

    @TableId
    private Long adminUserId;

    /**
     * 登录名称
     */
    private String username;

    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 是否锁定。0 为正常，1 为上锁。
     * 这里使用 mybatis-plus 的逻辑删除来实现
     */
    @TableLogic(value = "0", delval = "1")
    private Byte locked;
}