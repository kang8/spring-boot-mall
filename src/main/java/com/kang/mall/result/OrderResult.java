package com.kang.mall.result;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kang
 * ClassName: OrderResult
 * Create Date: 2021/4/9 15:34
 */
@Data
public class OrderResult {
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 订单总价
     */
    private BigDecimal totalPrice;
    /**
     * 支付状态
     */
    private Byte payStatus;
    /**
     * 支付方式
     */
    private Byte payType;
    /**
     * 订单状态
     */
    private Byte orderStatus;
    /**
     * 该订单的用户姓名
     */
    private String username;
    /**
     * 该订单的用户手机
     */
    private String phone;
    /**
     * 该订单的地址
     */
    private String address;
    /**
     * 该订单的商品信息
     */
    private List<OrderItemResult> orderItem;
}
