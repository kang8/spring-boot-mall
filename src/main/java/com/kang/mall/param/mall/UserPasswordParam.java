package com.kang.mall.param.mall;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author kang
 * Create Date: 2021/4/27 15:53
 */
@Data
public class UserPasswordParam {
    /**
     * 原始密码
     */
    @NotBlank(message = "原始密码不能为空")
    @Size(min = 6, max = 20, message = "原始密码的长度为 6-20 个字符")
    private String originalPassword;
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "新密码的长度为 6-20 个字符")
    private String newPassword;
}
