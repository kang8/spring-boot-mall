package com.kang.mall.param.admin.profile;

import com.kang.mall.param.admin.BaseAdminUserId;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yikang
 * ClassName: NameParam
 * Description: /admin/profile/name 入参
 * Create Date: 2021/1/20 19:34
 */
@Data
public class NameParam extends BaseAdminUserId {
    @NotBlank(message = "登陆名称不能为空")
    private String username;

    @NotBlank(message = "昵称不能为空")
    private String nickName;
}
