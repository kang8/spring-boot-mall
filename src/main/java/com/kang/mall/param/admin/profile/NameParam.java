package com.kang.mall.param.admin.profile;

import com.kang.mall.param.admin.BaseAdminUserId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yikang
 * ClassName: NameParam
 * Description: /admin/profile/name 入参
 * Create Date: 2021/1/20 19:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NameParam extends BaseAdminUserId {
    @NotBlank(message = "登陆名称不能为空")
    @Size(min = 6, max = 50, message = "登录名称的长度为 6-50")
    private String username;

    @NotBlank(message = "昵称不能为空")
    @Size(min = 2, max = 50, message = "昵称的长度为 2-50")
    private String nickName;
}
