package com.kang.mall.param.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: CarouselParam
 * Create Date: 2021/1/29 15:47
 */
@Data
public class CarouselParam implements Serializable {
    @NotBlank(message = "请上传轮播图")
    @Size(max = 100, message = "轮播图文件名可能过长，请将文件名缩短一点")
    private String carouselUrl;

    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @NotBlank(message = "跳转地址不能为空") // annotation !! NotBlank 只适用于 String
    @Size(max = 100, message = "跳转地址的长度不能超过 100")
    private String redirectUrl;

    @NotNull(message = "请传入排序值")
    @Range(min = Integer.MIN_VALUE, max = Integer.MAX_VALUE, message = "排序值不能超过 int 范围")
    private Integer carouselRank;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long updateUser;
}
