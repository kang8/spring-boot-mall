package com.kang.mall.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kang.mall.pojo.BaseCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
     * 是否删除
     */
    private Byte isDeleted;

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
