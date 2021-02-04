package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yikang
 * ClassName: Category
 * Create Date: 2021/2/1 15:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    /**
     * 排序等级
     */
    private Byte categoryLevel;
    /**
     * 父节点
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 排序值
     */
    private Integer categoryRank;
    /**
     * 是否删除。0 为 false 未删除，1 为true 已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Byte isDeleted;

    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class) // JSON 序列化 和 反序列化 的规则
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

    @TableField(exist = false)
    private List<Category> children;
}
