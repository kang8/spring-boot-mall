package com.kang.mall.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kang
 * ClassName: OrderResult
 * Create Date: 2021/4/9 15:34
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    /**
     * 创建订单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
}
