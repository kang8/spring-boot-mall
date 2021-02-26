package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: Carousel
 * Create Date: 2021/1/27 14:53
 */
@Data
public class Carousel implements Serializable {

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
     * 是否删除。0 为 false 未删除，1 为true 已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;

    /**
     * 这里 JsonFormat 的作用是格式 json 为 "yyyy-MM-dd HH:mm:ss"
     * 后端传给前端的数据会个格式成为 "yyyy-MM-dd HH:mm:ss" 的字符串
     * 前端传给后端的数据也要是 "yyyy-MM-dd HH:mm:ss" 形式的字符串，再有后端在进行相应转换
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long updateUser;

}
