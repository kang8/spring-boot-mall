package com.kang.mall.param.mall;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author kang
 * ClassName: LoginParam
 * Create Date: 2021/3/15 15:51
 */
@Data
public class LoginParam {
    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号为长度为 11 的数字")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为 6 ~ 20 个字符")
    private String password;
}
