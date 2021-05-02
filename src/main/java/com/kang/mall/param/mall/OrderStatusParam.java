package com.kang.mall.param.mall;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author kang
 * ClassName: OrderStatusParam
 * Create Date: 2021/4/13 11:45
 */
@Data
public class OrderStatusParam {
    /**
     * 支付方式
     */
    @Range(max = 3L, message = "支付方式范围错误")
    private Byte payType;
    /**
     * 订单状态
     */
    @Range(min = -3L, max = 5L, message = "订单状态范围错误")
    private Byte orderStatus;
}
