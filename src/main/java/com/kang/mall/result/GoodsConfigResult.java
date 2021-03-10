package com.kang.mall.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: GoodsConfigResult
 * Description: 商品配置返回类
 * Create Date: 2021/3/10 11:10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsConfigResult implements Serializable {
    /**
     * 配置 ID
     */
    private Long configId;
    /**
     * 配置排序值
     */
    private Integer configRank;
    /**
     * 商品图片
     */
    private String goodsCoverImage;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品售价
     */
    private BigDecimal sellingPrice;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
}
