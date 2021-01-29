package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: Carousel
 * Create Date: 2021/1/27 14:53
 */
@Data
public class Carousel {

    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @TableId(type = IdType.AUTO) //主键的类型为自动递增
    @JsonSerialize(using = ToStringSerializer.class) // Long 转换成 JSON 时，转换成字符串。这是因为 javascript number 类型（只能准确表示 (-2^53~2^53)）可能会丢失 Long 的进度。
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
     * 是否删除，0 为 false 未删除，1 为true 已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long updateUser;

}
