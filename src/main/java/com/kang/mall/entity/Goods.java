package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yikang
 * ClassName: Goods
 * Create Date: 2021/2/22 16:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Goods extends BaseEntity implements Serializable {
    /**
     * 商品主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品简介
     */
    private String goodsIntroduce;
    /**
     * 三级分类 ID，注意这里关联的分类 ID 的等级只能为最低级，也就是 3
     */
    private Long categoryId;
    /**
     * 商品主图
     */
    private String goodsCoverImage;
    /**
     * 商品详情
     */
    private String goodsDetailContent;
    /**
     * 商品价格
     */
    private BigDecimal originalPrice;
    /**
     * 商品实际价格
     */
    private BigDecimal sellingPrice;
    /**
     * 商品库存数
     */
    private Integer stockNum;
    /**
     * 标签
     */
    private String tag;
    /**
     * 商品上架状态
     * false -> 下架
     * true -> 上架
     */
    private Boolean goodsSellStatus;
}
