package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kang.mall.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yikang
 * ClassName: Goods
 * Create Date: 2021/2/22 16:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Goods extends BaseEntity implements Serializable {

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
     * 商品轮播图
     */
    private String goodsCarousel;

    /**
     * 商品详情
     */
    private String goodsDetailContent;

    /**
     * 商品价格
     */
    private Integer originalPrice;

    /**
     * 商品实际价格
     */
    private Integer sellingPrice;

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
     * 0 -> 下架
     * 1 -> 上架
     */
    private Byte goodsSellStatus;
}
