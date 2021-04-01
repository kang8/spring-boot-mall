package com.kang.mall.result;

import com.kang.mall.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kang
 * ClassName: UserResult
 * Create Date: 2021/4/1 14:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserResult extends User {
    /**
     * 该用户的购物车总量
     */
    private Integer cartCount;
}
