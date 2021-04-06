package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yikang
 * ClassName: Carousel
 * Create Date: 2021/1/27 14:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Carousel extends BaseEntity implements Serializable {
    /**
     * 轮播图主键 ID
     */
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @TableId(type = IdType.AUTO) //主键的类型为自动递增
    private Long carouselId;
    /**
     * 轮播图图片地址
     */
    private String carouselUrl;
    /**
     * 点击后跳转地址
     */
    private String redirectUrl;
    /**
     * 排序值
     */
    private Integer carouselRank;
    /**
     * 是否删除
     */
    private Byte isDeleted;

}
