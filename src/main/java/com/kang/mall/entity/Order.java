package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order extends BaseTimeEntity {
    /**
     * 订单主键 ID
     */
    @TableId(type = IdType.AUTO)
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
    private Byte isDeleted;
}
