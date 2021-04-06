package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author kang
 * ClassName: OrderItem
 * Create Date: 2021/4/2 21:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {
    /**
     * 订单关联表主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Long orderItemId;
    /**
     * 订单 ID
     */
    private Long orderId;
    /**
     * 商品 ID
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsCoverImage;
    /**
     * 商品价格
     */
    private BigDecimal sellingPrice;
    /**
     * 数量
     */
    private Integer goodsCount;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
}
