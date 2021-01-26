package com.kang.mall.param.admin.profile;

import com.kang.mall.param.admin.BaseAdminUserId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yikang
 * ClassName: PasswordParam
 * Description: /admin/profile/password 入参
 * Create Date: 2021/1/20 21:05
 */
@Data
public class PasswordParam extends BaseAdminUserId {
    @NotBlank(message = "原始密码不能为空")
    @Size(min = 6, max = 20, message = "元素密码长度为 6 ~ 20 个字符")
    private String originPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "新密码长度为 6 ~ 20 个字符")
    private String newPassword;
}
