package com.kang.mall.param.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author kang
 * Create Date: 2021/5/21 16:29
 */
@Data
public class AdminUserParam {
    @NotBlank(message = "登陆名称不能为空")
    @Size(min = 6, max = 50, message = "登录名称的长度为 6-50")
    private String username;

    @NotBlank(message = "昵称不能为空")
    @Size(min = 2, max = 50, message = "昵称的长度为 2-50")
    private String nickName;
}
