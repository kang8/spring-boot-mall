package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kang.mall.entity.base.BaseTimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author kang
 * ClassName: Order
 * Create Date: 2021/4/2 21:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseTimeEntity {
    /**
     * 订单主键 ID
     */
    private Long orderId;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 订单价格
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
     * 用户姓名
     */
    private String username;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;
}
