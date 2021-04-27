package com.kang.mall.param.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author kang
 * ClassName: UserParam
 * Create Date: 2021/3/14 17:47
 */
@Data
public class UserInfoParam {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(max = 60, message = "用户名的长度不能超过 60 个字符")
    private String username;
    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    @Size(max = 60, message = "地址的长度不能超过 60 个字符")
    private String address;
}
