package com.kang.mall.param.admin;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yikang
 * ClassName: BaseAdminUserId
 * Description:
 * Create Date: 2021/1/20 19:42
 */
@Data
public class BaseAdminUserId implements Serializable {
    /**
     * NOTE: Long, Integer 等 Number 不能用 @NotBlank 来验证。
     * 因为这里 Blank 指的是空格，换行符等空字符串
     */
    @NotNull(message = "请传入用户 ID")
    @Min(value = 0,message = "请检查 adminUserId 是否合法")
    private Long adminUserId;
}
