package com.kang.mall.param.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yikang
 * ClassName: LoginParam
 * Description:
 * Create Date: 2021/1/16 17:54
 */
@Data
public class LoginParam implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为 6 ~ 20 个字符")
    private String password;
}
