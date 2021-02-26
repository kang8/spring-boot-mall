package com.kang.mall.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.kang.mall.pojo.BaseCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: Category
 * Create Date: 2021/2/1 15:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Category extends BaseCategory {
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
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

    public Category(Long categoryId, Byte categoryLevel, Long parentId, String categoryName) {
        super(categoryId, categoryLevel, parentId, categoryName, null);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryRank=" + categoryRank +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                ", categoryId=" + categoryId +
                ", categoryLevel=" + categoryLevel +
                ", parentId=" + parentId +
                ", categoryName='" + categoryName + '\'' +
                ", children=" + children +
                '}';
    }
}
