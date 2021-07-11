package com.kang.mall.param.admin;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author kang
 * Create Date: 2021/5/1 14:44
 */
@Data
public class UserParam {
    /**
     * 是否锁定
     * 0 未锁定
     * 1 锁定
     */
    @Range(max = 1L, message = "isLicked 只能传入 0 或 1")
    private Byte isLocked;
}
