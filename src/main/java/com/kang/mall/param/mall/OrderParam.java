package com.kang.mall.param.mall;

import com.kang.mall.result.CartResult;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kang
 * ClassName: OrderParam
 * Create Date: 2021/4/6 11:24
 */
@Data
public class OrderParam {
    /**
     * 购物车商品列表
     */
    @NotEmpty(message = "请选择结算的商品")
    private List<CartResult> cartGoodsList;
    /**
     * 商品总价
     */
    @DecimalMin(value = "0.0", inclusive = false, message = "总价必须大于 0")
    @Digits(integer = 17, fraction = 2)
    @NotNull(message = "请传入商品总价")
    private BigDecimal totalPrice;
    /**
     * 姓名
     */
    @NotEmpty(message = "请输入姓名")
    private String username;
    /**
     * 地址
     */
    @NotEmpty(message = "请输入地址")
    private String address;
    /**
     * 电话
     */
    @NotEmpty(message = "请输入电话")
    @Size(min = 11, max = 11, message = "电话号码长度为 11 位")
    private String phone;
}
